package io.github.singhalmradul.likeservice.repositories;

import java.util.UUID;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import io.github.singhalmradul.likeservice.model.Like;
import io.github.singhalmradul.likeservice.model.LikeKey;
import reactor.core.publisher.Mono;

public interface LikeRepository extends ReactiveCrudRepository<Like, LikeKey> {

    @Query("""
        SELECT COUNT(user_id)
        FROM like
        WHERE post_id = :postId
    """)
    Mono<Long> countLikesByPostId(

            @Param("postId")
            UUID postId
    );

}
