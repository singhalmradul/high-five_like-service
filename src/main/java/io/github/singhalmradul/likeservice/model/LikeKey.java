package io.github.singhalmradul.likeservice.model;

import static org.springframework.data.cassandra.core.cql.Ordering.ASCENDING;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyClass
public class LikeKey {

    @PrimaryKeyColumn(
        name = "post_id",
        ordinal = -9,
        type = PARTITIONED
    )
    private UUID postId;

    @PrimaryKeyColumn(
        name = "user_id",
        ordinal = -8,
        ordering = ASCENDING
    )
    private UUID userId;
}
