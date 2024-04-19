package io.github.singhalmradul.likeservice.handlers;

import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

public interface LikeHandler {

    ServerResponse getLikesCountByPostId(ServerRequest request);

    ServerResponse like(ServerRequest request);

    ServerResponse unlike(ServerRequest request);

    ServerResponse isLikedByUser(ServerRequest request);

    ServerResponse getAllLikes(ServerRequest request);

    ServerResponse getLikesByPostId(ServerRequest request);
}