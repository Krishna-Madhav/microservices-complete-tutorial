package com.lcwd.user.service.controller;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private int retryCount = 1;

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    // create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }


    // get single user

    @GetMapping("/{userId}")
    // @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    // @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable String userId) {

        logger.info("Get single user : UserController");
        logger.info("Retry count : {}", retryCount);
        retryCount++;

        User user = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    // creating fallback method for circuitbreaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception exception) {

        logger.info("Fallback is executed because service is down: ", exception.getMessage());

        User random_user = User.builder()
                .about("This user is dummy user because service is down")
                .userName("Dummy Name")
                .email("dummy@email.com")
                .userId("12345qwerty")
                .build();

        return new ResponseEntity<>(random_user, HttpStatus.OK);
    }

    // get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
}