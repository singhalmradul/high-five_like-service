package io.github.singhalmradul.likeservice.handlers;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.github.singhalmradul.likeservice.services.LikeService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public class LikeHandler {

    private LikeService likeService;

    public Mono<ServerResponse> getLikesCountByPostId(ServerRequest request){

        UUID postId = UUID.fromString(request.pathVariable("postId"));

        Mono<Long> likesCount = likeService.getLikesCountByPostId(postId);
        
        return (
            ok()
            .contentType(APPLICATION_JSON)
            .body(likesCount, Long.class)
        );
    }
}
