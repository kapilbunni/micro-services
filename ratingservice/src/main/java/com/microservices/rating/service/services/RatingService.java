package com.microservices.rating.service.services;

import com.microservices.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating createRating(Rating rating);
    List<Rating> getAllRatings();
    List<Rating> getRatingByUserId(String id);
    List<Rating> getRatingByHotelId(String id);
}
