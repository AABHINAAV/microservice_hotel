package com.examMS.RatingMS.Controllers;

import com.examMS.RatingMS.Entities.Rating;
import com.examMS.RatingMS.Services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/createRating")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        Rating res = this.ratingService.createRating(rating);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/getAllRatings")
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> allRatings = this.ratingService.getAllRatings();
        return new ResponseEntity<>(allRatings, HttpStatus.OK);
    }

    @GetMapping("/getRatingByRatingId/{ratingId}")
    public ResponseEntity<Rating> getRatingByRatingId(@PathVariable("ratingId") String ratingId) {
        Rating res = this.ratingService.getRatingByRatingId(ratingId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/getAllRatingByUserId/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingByUserId(@PathVariable("userId") String userId) {
        List<Rating> allRatingByUserId = this.ratingService.getAllRatingByUserId(userId);
        return new ResponseEntity<>(allRatingByUserId, HttpStatus.OK);
    }

    @GetMapping("/getAllRatingByHotelId/{hotelId}")
    public ResponseEntity<List<Rating>> getAllRatingByHotelId(@PathVariable("hotelId") String hotelId) {
        List<Rating> allRatingByHotelId = this.ratingService.getAllRatingByHotelId(hotelId);
        return new ResponseEntity<>(allRatingByHotelId, HttpStatus.OK);
    }

    @DeleteMapping("/deleteRatingByRatingId/{ratingId}")
    public ResponseEntity<String> deleteRatingByRatingId(@PathVariable("ratingId") String ratingId) {
        this.ratingService.deleteRatingByRatingId(ratingId);
        return new ResponseEntity<>("Rating deleted Successfully", HttpStatus.OK);
    }
}
