package bistros.router;

import bistros.handler.HelloHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class HelloRouter {
    HelloHandler helloHandler = new HelloHandler();
    @Bean
    RouterFunction<ServerResponse> router() {
        return route(GET("/hello"), helloHandler::helloWorld)
            .andRoute(GET("/time"), helloHandler::time)
            .andRoute(DELETE("/delete"), helloHandler::printlog)
            .andRoute(GET("/param/{name}"), helloHandler::getName)
            .andRoute(POST("/param/body").and(accept(APPLICATION_JSON)), helloHandler::getBody);
    }
}
