package com.lcwd.rating.services;

import com.lcwd.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    // Create
    Rating createRating(Rating rating);

    // Get all ratings
    List<Rating> getRatings();

    // Get all ratings based on UserId
    List<Rating> getRatingsByUserId(String userId);

    // Get all rating based on HotelId
    List<Rating> getRatingsByHotelId(String userId);

}