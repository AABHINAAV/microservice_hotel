package com.examMS.RatingMS.Services.ServiceImplementations;

import com.examMS.RatingMS.Entities.Rating;
import com.examMS.RatingMS.Exception.ResourceNotFoundException;
import com.examMS.RatingMS.Repositories.RatingRepository;
import com.examMS.RatingMS.Services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        return this.ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        List<Rating> allRatings = this.ratingRepository.findAll();
        if (allRatings.isEmpty()) {
            throw new ResourceNotFoundException("No ratings present");
        }

        return allRatings;
    }

    @Override
    public Rating getRatingByRatingId(String ratingId) {
        return this.ratingRepository.findById(ratingId).orElseThrow(
                () -> new ResourceNotFoundException("No such rating present with id : " + ratingId)
        );
    }

    @Override
    public List<Rating> getAllRatingByUserId(String userId) {
        List<Rating> allRatings = this.ratingRepository.findAllByUserId(userId);
        if (allRatings.isEmpty()) {
            throw new ResourceNotFoundException("No ratings provided by user with user id : " + userId);
        }

        return allRatings;
    }

    @Override
    public List<Rating> getAllRatingByHotelId(String hotelId) {
        List<Rating> allRatings = this.ratingRepository.findAllByHotelId(hotelId);
        if (allRatings.isEmpty()) {
            throw new ResourceNotFoundException("No rating present for hotel with hotel id : " + hotelId);
        }

        return allRatings;
    }

    @Override
    public void deleteRatingByRatingId(String ratingId) {
        this.getRatingByRatingId(ratingId);
        this.ratingRepository.deleteById(ratingId);
    }
}
