package io.github.singhalmradul.likeservice.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebInputException;

import io.github.singhalmradul.likeservice.model.Like;
import io.github.singhalmradul.likeservice.model.LikeKey;
import io.github.singhalmradul.likeservice.model.Operation;
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

    @Override
    public boolean perform(UUID postId, UUID userId, Operation operation) {

        if (operation == Operation.LIKE) {
            // validate(postId, userId);
            return repository.save(Like.builder().postId(postId).userId(userId).build()) != null;
        } else {
            repository.delete(Like.builder().postId(postId).userId(userId).build());
            return true;
        }
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

        System.out.println("\033[1;91m"+"post_id= " + postId+ ",user_id= " + userId+ ",is_liked= " + repository.existsById(new LikeKey(postId, userId))+"\033[0m" );
        return repository.existsById(new LikeKey(postId, userId));
    }
}
