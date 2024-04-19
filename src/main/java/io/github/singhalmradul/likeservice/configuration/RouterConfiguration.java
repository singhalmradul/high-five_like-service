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
                .DELETE("/{userId}", likeHandler::unlike)
                .GET(likeHandler::getLikesByPostId)
                .POST(likeHandler::like)
            )
            .path("/likes", builder -> builder
                .GET(likeHandler::getAllLikes)
            )
            .build()
        );
    }
}
