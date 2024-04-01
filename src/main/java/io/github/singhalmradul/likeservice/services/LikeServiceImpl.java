package io.github.singhalmradul.likeservice.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.singhalmradul.likeservice.repositories.LikeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class LikeServiceImpl implements LikeService {

    private LikeRepository likeRepository;

    @Override
    public long getLikesCountByPostId(UUID postId) {

        return likeRepository.countLikesByPostId(postId);
    }
}
