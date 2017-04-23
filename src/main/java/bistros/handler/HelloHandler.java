package bistros.handler;

import bistros.model.User;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

public class HelloHandler {

    public Mono<ServerResponse> helloWorld(ServerRequest request){
        return ok().body(fromObject("Hello-World"));
    }

    public Mono<ServerResponse> time(ServerRequest request){
        return ok().body(fromObject(new Date()));
    }

    public Mono<ServerResponse> printlog(ServerRequest request){
        System.out.println("delete");
        return ServerResponse.ok().body(Mono.empty());
    }

    public Mono<ServerResponse> getName(ServerRequest request){
        String name = request.pathVariable("name");
        return ok().body(fromObject(name));
    }

    //curl -H "Content-Type: application/json" -X POST -d '{"id":"id","name":"name"}' http://localhost:8080/param/body
    public Mono<ServerResponse> getBody(ServerRequest request){
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<User> user = request.bodyToMono(User.class);
        return user.flatMap(u -> ok().body(u).switchIfEmpty(notFound));
    }
}
