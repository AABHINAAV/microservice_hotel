package com.examMS.RatingMS.Services;

import com.examMS.RatingMS.Entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {
    public Rating createRating(Rating rating);

    public List<Rating> getAllRatings();

    public Rating getRatingByRatingId(String ratingId);

    public List<Rating> getAllRatingByUserId(String userId);

    public List<Rating> getAllRatingByHotelId(String hotelId);

    public void deleteRatingByRatingId(String ratingId);
}
