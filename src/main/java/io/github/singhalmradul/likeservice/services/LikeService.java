package io.github.singhalmradul.likeservice.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.singhalmradul.likeservice.repositories.LikeRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class LikeService {

    private LikeRepository likeRepository;

    public Mono<Long> getLikesCountByPostId(UUID postId) {

        return likeRepository.countLikesByPostId(postId);
    }
}
