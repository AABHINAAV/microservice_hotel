package com.examMS.UserMS.Services.ServiceImplementation;

import com.examMS.UserMS.Entities.Hotel;
import com.examMS.UserMS.Entities.Rating;
import com.examMS.UserMS.Entities.User;
import com.examMS.UserMS.Exceptions.ResourceNotFoundException;
import com.examMS.UserMS.Repositories.UserRepository;
import com.examMS.UserMS.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RestTemplate restTemplate;

    @Override
    public User createUser(User user) {
        String newUserId = UUID.randomUUID().toString();
        user.setUserId(newUserId);
        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserByUserId(String userId) {
        User user = this.userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("No such user present with user id : " + userId)
        );

        Rating[] allRatingsArray = this.restTemplate.getForObject(
                "http://RATING-SERVICE/ratings/getAllRatingByUserId/" + userId,
                Rating[].class
        );

        List<Rating> allRatings = Arrays.stream(allRatingsArray).toList();

        List<Rating> ratings =  allRatings.stream().map(
                rating -> {
                    String hotelId = rating.getHotelId();
                    Hotel hotelDetails = this.restTemplate.getForObject(
                            "http://HOTEL-SERVICE/hotels/getHotelByHotelId/" + hotelId,
                            Hotel.class
                    );

                    rating.setHotel(hotelDetails);

                    return rating;
                }
        ).collect(Collectors.toList());

        user.setRatings(ratings);

        return user;
    }

    @Override
    public void deleteUserByUserId(String userId) {
        this.getUserByUserId(userId);
        this.userRepository.deleteById(userId);
    }
}
