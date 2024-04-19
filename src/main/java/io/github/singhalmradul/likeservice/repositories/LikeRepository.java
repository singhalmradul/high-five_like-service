package io.github.singhalmradul.likeservice.repositories;

import java.util.UUID;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.github.singhalmradul.likeservice.model.Like;
import io.github.singhalmradul.likeservice.model.LikeKey;

public interface LikeRepository extends CrudRepository<Like, LikeKey> {

    @Query("""
        SELECT COUNT(user_id)
        FROM like
        WHERE post_id = :postId
    """)
    long countLikesByPostId(@Param("postId") UUID postId);

    @Query("""
        SELECT *
        FROM like
        WHERE post_id = :postId
    """)
    Iterable<Like> findAllByPostId(@Param("postId") UUID postId);
}
