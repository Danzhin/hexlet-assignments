package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void create(Context ctx) {
        var firstName = ctx.formParam("firstName");
        var lastName = ctx.formParam("lastName");
        var email = ctx.formParam("email");
        var password = ctx.formParam("password");

        var encriptedPassword = Security.encrypt(password);
        var token = Security.generateToken();

        var user = new User(firstName, lastName, email, encriptedPassword, token);
        UserRepository.save(user);
        ctx.redirect(NamedRoutes.userPath(user.getId()));
        ctx.cookie("token", token);
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UserRepository.find(id).orElseThrow(() -> new NotFoundResponse("Id not found"));
        if (user.getToken().equals(ctx.cookie("token"))) {
            ctx.render("users/show.jte", model("page", new UserPage(user)));
        } else {
            ctx.redirect(NamedRoutes.buildUserPath());
        }
    }
    // END
}
