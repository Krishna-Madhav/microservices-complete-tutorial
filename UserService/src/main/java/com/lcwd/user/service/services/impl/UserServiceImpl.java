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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

        // Here User data doesn't contain information about rating as it is not being set here in this microservice. So , we need to fetch it from another microservice RatingService
        // fetch all the ratings given by a user using his userId
        // http://localhost:8083/ratings/users/user1

        // In order to make a call to another microservice for fetching data we need have a client in this class which can call the rest api exposed by another microservice
        // We can do this by RestTemplate or by using FeignClient

       /* Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{}", ratings);

        List<Rating> userRatings = Arrays.stream(ratings).toList();

        List<Rating> ratingList = userRatings.stream().map(rating -> {
            // api call to Hotel service to get hotel based on hotel id
            // http://localhost:8082/hotels/hotel1

            *//* This is using RestTemplate
              ResponseEntity<Hotel> hotel = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
              logger.info(" response status code {} ", hotel.getStatusCode());
            *//*

            // This call is using Open Feign Client

            Hotel hotelData = hotelService.getHotel(rating.getHotelId());

            // set the Hotel in Rating class
            rating.setHotel(hotelData);

            // return the rating
            return rating;

        }).collect(Collectors.toList());

        user.setRatings(ratingList);*/

        return user;
    }
}


