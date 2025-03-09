package exercise;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }

    

    @Test
    void testLoginFail1() throws Exception {

        HttpResponse<String> responsePost = Unirest
            .post(baseUrl + "/sessions")
            .field("name", "noname")
            .field("password", "secret")
            .asString();

        var body = responsePost.getBody();
        assertThat(body).contains("Wrong");
    }

    @Test
    void testLoginFail2() throws Exception {

        HttpResponse<String> responsePost = Unirest
            .post(baseUrl + "/sessions")
            .field("name", "admin")
            .field("password", "wrong_password")
            .asString();

        var body = responsePost.getBody();
        assertThat(body).contains("Wrong");
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }
}
