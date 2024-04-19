package io.github.singhalmradul.likeservice.model;

import java.util.UUID;

public record LikeRecord(UUID postId, User user) {}
