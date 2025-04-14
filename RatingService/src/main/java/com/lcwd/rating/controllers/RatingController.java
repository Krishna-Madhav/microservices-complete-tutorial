package com.emission.lcwd.rating.controllers;

import com.emission.lcwd.rating.entities.Rating;
import com.emission.lcwd.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    RatingService ratingService;

    // create new rating
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating){

        Rating newRating = ratingService.createRating(rating);
        System.out.println(newRating);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRating);

    }

    // get all ratings
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings(){
        List<Rating> ratings = ratingService.getRatings();
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId( @PathVariable  String userId){
        List<Rating> ratingsByUserId = ratingService.getRatingsByUserId(userId);
        return ResponseEntity.ok(ratingsByUserId);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId( @PathVariable  String hotelId){
        List<Rating> ratingsByHotelId = ratingService.getRatingsByHotelId(hotelId);
        return ResponseEntity.ok(ratingsByHotelId);
    }
}
