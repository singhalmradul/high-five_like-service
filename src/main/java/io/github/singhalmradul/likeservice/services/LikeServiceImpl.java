package io.github.singhalmradul.likeservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebInputException;

import io.github.singhalmradul.likeservice.model.Like;
import io.github.singhalmradul.likeservice.model.LikeKey;
import io.github.singhalmradul.likeservice.model.LikeRecord;
import io.github.singhalmradul.likeservice.proxies.PostServiceProxy;
import io.github.singhalmradul.likeservice.proxies.UserServiceProxy;
import io.github.singhalmradul.likeservice.repositories.LikeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class LikeServiceImpl implements LikeService {

    private final LikeRepository repository;
    private final UserServiceProxy userServiceProxy;
    private final PostServiceProxy postServiceProxy;

    @Override
    public long getLikesCountByPostId(UUID postId) {

        return repository.countLikesByPostId(postId);
    }

    private void validate(UUID postId, UUID userId) {

        boolean valid = userServiceProxy.existsByUserId(userId)
                && postServiceProxy.existsByPostId(postId);

        if (!valid) {
            throw new ServerWebInputException("Invalid userId or postId");
        }
    }

    @Override
    public boolean isLikedByUser(UUID postId, UUID userId) {

        return repository.existsById(new LikeKey(postId, userId));
    }

    @Override
    public void like(UUID postId, UUID userId) {
        System.out.println("" +
            "\033[1;91m" +
            "user_id= " + userId + " liked " + "post_id= " + postId+ "," +
            "\033[0m"
        );
        validate(postId, userId);
        repository.save(Like.builder().postId(postId).userId(userId).build());
    }

    @Override
    public void unlike(UUID postId, UUID userId) {
        System.out.println("" +
            "\033[1;91m" +
            "user_id= " + userId + " unliked " + "post_id= " + postId+ "," +
            "\033[0m"
        );
        repository.delete(Like.builder().postId(postId).userId(userId).build());
    }

    @Override
    public List<LikeRecord> getAllLikes() {

        List<LikeRecord> likes = new ArrayList<>();

        repository.findAll().forEach(like ->
            likes.add(new LikeRecord(
                like.getPostId(),
                userServiceProxy.getUserById(like.getUserId())
            ))
        );

        return likes;
    }

    @Override
    public List<LikeRecord> getLikesByPostId(UUID postId) {

            List<LikeRecord> likes = new ArrayList<>();

            repository.findAllByPostId(postId).forEach(like ->
                likes.add(new LikeRecord(
                    like.getPostId(),
                    userServiceProxy.getUserById(like.getUserId())
                ))
            );

            return likes;
    }
}
