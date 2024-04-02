package io.github.singhalmradul.likeservice.model;

import java.util.UUID;

import lombok.Data;

@Data
public class PatchRequestBody {

    Operation op;
    UUID userId;
}
