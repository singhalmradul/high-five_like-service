package io.github.singhalmradul.likeservice.configuration;

import static org.springframework.web.servlet.function.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import io.github.singhalmradul.likeservice.handlers.LikeHandler;

@Configuration
public class RouterConfiguration {

    @Bean
    RouterFunction<ServerResponse> likeRouter(LikeHandler likeHandler) {

        return (
            route()
            .GET("/posts/{postId}/likes/count", likeHandler::getLikesCountByPostId)
            .PATCH("/posts/{postId}/likes", likeHandler::likeOrUnlike)
            .build()
        );
    }
}
