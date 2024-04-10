package io.github.singhalmradul.likeservice.services;

import java.util.UUID;

import io.github.singhalmradul.likeservice.model.Like;

public interface LikeService {

    long getLikesCountByPostId(UUID postId);

    void like(UUID postId, UUID userId);

    void unlike(UUID postId, UUID userId);

    boolean isLikedByUser(UUID postId, UUID userId);

    Iterable<Like> getAllLikes();

}