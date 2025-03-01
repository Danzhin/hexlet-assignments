package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import java.util.List;

import exercise.dto.posts.PostsPage;
import exercise.model.Post;
import exercise.dto.posts.PostPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    private static final int PAGE_SIZE = 5;

    public static void show(Context ctx) {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);
        var post = PostRepository.find(id).orElseThrow(() -> new NotFoundResponse("Page not found"));
        ctx.render("posts/show.jte", model("page", new PostPage(post)));
    }

    public static void index(Context ctx) {
        int page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        List<Post> posts = PostRepository.findAll(page - 1, PAGE_SIZE);
        ctx.render("posts/index.jte", model("page", new PostsPage(posts)));
    }
    // END

}
