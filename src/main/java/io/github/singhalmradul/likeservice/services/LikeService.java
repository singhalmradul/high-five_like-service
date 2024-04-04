package io.github.singhalmradul.likeservice.services;

import java.util.UUID;

import io.github.singhalmradul.likeservice.model.Operation;

public interface LikeService {

    long getLikesCountByPostId(UUID postId);

    boolean perform(UUID postId, UUID userId, Operation operation);

    boolean isLikedByUser(UUID postId, UUID userId);
}