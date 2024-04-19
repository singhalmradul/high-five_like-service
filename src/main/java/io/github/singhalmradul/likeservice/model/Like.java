package io.github.singhalmradul.likeservice.model;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Like {

    @PrimaryKey
    private LikeKey key;

    public static LikeBuilder builder() {
        return new LikeBuilder();
    }

    public UUID getPostId() {
        return key.getPostId();
    }

    public UUID getUserId() {
        return key.getUserId();
    }

    public static class LikeBuilder {

        private LikeBuilder() {
        }

        private UUID postId;

        private UUID userId;

        public LikeBuilder postId(UUID postId) {
            this.postId = postId;
            return this;
        }

        public LikeBuilder userId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public Like build() {
            return new Like(new LikeKey(postId, userId));
        }

    }
}
