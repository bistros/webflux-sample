package bistros.router;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.web.reactive.server.WebTestClient;


public class HelloRouterTest {
    static WebTestClient client;
    static HelloRouter router;

    @BeforeClass
    public static void before() {
        router = new HelloRouter();
        client = WebTestClient
            .bindToRouterFunction(router.router())
            .build();
    }

    @Test
    public void testHello() {
        client.get()
              .uri("/hello")
              .exchange()
              .expectStatus()
              .is2xxSuccessful()
              .expectBody(String.class)
              .isEqualTo("Hello-World");
    }

    @Test
    public void testParameter() {
        client.get()
              .uri("/param/" + "tk")
              .exchange()
              .expectStatus()
              .is2xxSuccessful()
              .expectBody(String.class)
              .isEqualTo("tk");
    }
}