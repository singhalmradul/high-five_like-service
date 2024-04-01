package io.github.singhalmradul.likeservice.services;

import java.util.UUID;

public interface LikeService {

    long getLikesCountByPostId(UUID postId);

}