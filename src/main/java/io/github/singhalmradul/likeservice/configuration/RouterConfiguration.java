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
            .path("/posts/{postId}/likes", builder -> builder
                .GET("/count", likeHandler::getLikesCountByPostId)
                .GET("/{userId}", likeHandler::isLikedByUser)
                .POST(likeHandler::like)
                .DELETE("/{userId}", likeHandler::unlike)
            )
            .path("/likes", builder -> builder
                .GET(likeHandler::getAllLikes)
            )
            .build()
        );
    }
}
