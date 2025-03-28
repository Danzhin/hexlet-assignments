package exercise;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import exercise.model.User;
import exercise.dto.users.UsersPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

import org.apache.commons.lang3.StringUtils;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users", ctx -> {
            var term = ctx.queryParam("term");
            List<User> users = filterUsers(term, USERS);
            var page = new UsersPage(users, term);
            ctx.render("users/index.jte", model("page", page));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    private static List<User> filterUsers(String term, List<User> users) {
        if (StringUtils.isNotBlank(term)) {
            return new ArrayList<>(USERS.stream()
                .filter(user -> user.getFirstName().toLowerCase().startsWith(term.toLowerCase()))
                .collect(Collectors.toList()));
        }
        return new ArrayList<>(users);
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
