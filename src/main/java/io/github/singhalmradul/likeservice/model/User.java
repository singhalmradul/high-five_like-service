package io.github.singhalmradul.likeservice.model;

import java.util.UUID;

public record User(UUID id, String displayName, String avatar) {}
