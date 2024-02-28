package io.github.singhalmradul.likeservice.configuration;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.github.singhalmradul.likeservice.handlers.LikeHandler;

@Configuration
public class RouterConfiguration {

    @Bean
    RouterFunction<ServerResponse> likeRouter(LikeHandler likeHandler) {

        return (
            route()
            .GET("/posts/{postId}/likes", likeHandler::getLikesCountByPostId)
            .build()
        );
    }
}
