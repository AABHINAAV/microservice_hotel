package com.examMS.RatingMS.Repositories;

import com.examMS.RatingMS.Entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {
    public List<Rating> findAllByUserId(String userId);

    public List<Rating> findAllByHotelId(String hotelId);
}
