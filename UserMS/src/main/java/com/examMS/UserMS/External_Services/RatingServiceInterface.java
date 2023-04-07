package com.examMS.UserMS.External_Services;

import com.examMS.UserMS.Entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingServiceInterface {

    @GetMapping("/ratings/getAllRatingByUserId/{userId}")
    public List<Rating> getAllRatingByUserId(@PathVariable("userId") String userId);
}
