package io.github.singhalmradul.likeservice.proxies;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserServiceProxy {

    @GetMapping("/users/{userId}/exists")
    boolean existsByUserId(@PathVariable("userId") UUID userId);
}