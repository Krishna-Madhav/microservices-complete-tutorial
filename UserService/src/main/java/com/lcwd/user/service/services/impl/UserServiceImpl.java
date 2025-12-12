package com.lcwd.user.service.services.impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    // Used for making REST calls to other services
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {

        // Generate random user id
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        // Retrieve all ratings from Rating Service using RestTemplate
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {

        // Get user from database using UserRepository
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with given id " + userId + " not found on server "));

        /*
         Here, User data doesn't contain information about rating as it is not being set here in this User microservice.
         So, we need to fetch it from another microservice RatingService which exposes an endpoint for the same.
         Using this end-point, we fetch all the ratings given by a user using his userId
         e.g. http://localhost:8083/ratings/users/user1

         In order to make a call to another microservice for fetching data, we need to have a client in this class which can call the rest api exposed
         by another microservice.

         We can do this by RestTemplate or by using FeignClient.

         */



        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            /*
                Here, at this point of time ratingsOfUser contains values for the Rating object - however, it doesn't contain information about Hotel object which is
                part of Rating object. So, we need to make a call to Hotel service using hotelId and fetch the details and store to Rating object one by one

                http://localhost:8082/hotels/cd96bd11-dcc6-4771-934f-991847c75ff8
            */
                ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);

                logger.info("response {} ", forEntity.getStatusCode());

                Hotel hotel = forEntity.getBody();

                // set the hotel to rating object
                rating.setHotel(hotel);

                // return the rating
            return rating;
        }).collect(Collectors.toList());

        // set ratings to user
        user.setRatings(ratingList);
        return user;
    }
}