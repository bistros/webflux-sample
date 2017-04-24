package bistros;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Created by 1003920 on 2017. 4. 24..
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebfluxSampleApplication.class)
public class IntegrationTest {
    WebTestClient client;

    @Before
    public void setUp() throws Exception {
        client = WebTestClient
            .bindToServer()
            .baseUrl("http://localhost:8080")
            .build();
    }

    @Test
    public void server() {
        client.get()
              .uri("/hello")
              .exchange()
              .expectStatus()
              .is2xxSuccessful();
    }
}
