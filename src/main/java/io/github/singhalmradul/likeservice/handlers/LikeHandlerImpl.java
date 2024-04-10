package io.github.singhalmradul.likeservice.handlers;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.ServerResponse.ok;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import io.github.singhalmradul.likeservice.model.IdOnly;
import io.github.singhalmradul.likeservice.services.LikeService;
import jakarta.servlet.ServletException;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public class LikeHandlerImpl implements LikeHandler {

    private static final String POST_ID = "postId";

    private final LikeService likeService;

    @Override
    public ServerResponse getLikesCountByPostId(ServerRequest request) {

        UUID postId = UUID.fromString(request.pathVariable(POST_ID));

        int likesCount = (int) likeService.getLikesCountByPostId(postId);

        return (
            ok()
            .contentType(APPLICATION_JSON)
            .body(likesCount)
        );
    }

    @Override
    public ServerResponse isLikedByUser(ServerRequest request) {

        UUID userId = UUID.fromString(request.pathVariable("userId"));
        UUID postId = UUID.fromString(request.pathVariable(POST_ID));

        boolean status = likeService.isLikedByUser(postId, userId);

        return (
            ok()
            .contentType(APPLICATION_JSON)
            .body(status)
        );
    }

    @Override
    public ServerResponse like(ServerRequest request) {
        try {

            UUID postId = UUID.fromString(request.pathVariable(POST_ID));
            UUID userId = request.body(IdOnly.class).id();

            likeService.like(postId, userId);

            return ok().build();

        } catch (ServletException | IOException | IllegalArgumentException | NullPointerException e) {

            throw new ServerWebInputException(e.getMessage());
        }
    }

    @Override
    public ServerResponse unlike(ServerRequest request) {
        try {

            UUID postId = UUID.fromString(request.pathVariable(POST_ID));
            UUID userId = UUID.fromString(request.pathVariable("userId"));

            likeService.unlike(postId, userId);

            return ok().build();

        } catch (IllegalArgumentException | NullPointerException e) {

            throw new ServerWebInputException(e.getMessage());
        }
    }

    @Override
    public ServerResponse getAllLikes(ServerRequest request) {

            return (
                ok()
                .contentType(APPLICATION_JSON)
                .body(likeService.getAllLikes())
            );
    }
}
