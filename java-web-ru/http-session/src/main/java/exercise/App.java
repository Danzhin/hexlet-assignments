package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_PER_PAGE = 5;

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            int page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(DEFAULT_PAGE);
            int perPage = ctx.queryParamAsClass("per", Integer.class).getOrDefault(DEFAULT_PER_PAGE);

            int fromIndex = (page - 1) * perPage;
            int toIndex = Math.min(fromIndex + perPage, USERS.size());

            List<Map<String, String>> paginatedUsers = USERS.subList(
                    Math.max(0, fromIndex),
                    Math.max(0, toIndex)
            );

            ctx.json(paginatedUsers);
        });
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
