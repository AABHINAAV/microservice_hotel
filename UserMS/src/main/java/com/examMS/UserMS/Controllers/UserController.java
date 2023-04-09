package com.examMS.UserMS.Controllers;

import com.examMS.UserMS.Entities.User;
import com.examMS.UserMS.Payload.ApiResponse;
import com.examMS.UserMS.Services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User res = this.userService.createUser(user);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    //
    int retryCount = 1;
    //

    //    @CircuitBreaker(name = "getUserCallsRatingAndHotel", fallbackMethod = "getUserCallsRatingAndHotel_fallback")
//    @Retry(name = "getUserCallsRatingAndHotel", fallbackMethod = "getUserCallsRatingAndHotel_fallback")
    @RateLimiter(name = "getUserCallsRatingAndHotel", fallbackMethod = "getUserCallsRatingAndHotel_fallback")
    @GetMapping("/getUserByUserId/{userId}")
    public ResponseEntity<User> getUserByUserId(@PathVariable("userId") String userId) {
        System.out.println("retry count : " + retryCount);
        retryCount++;
        User res = this.userService.getUserByUserId(userId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<User> getUserCallsRatingAndHotel_fallback(String userId, Exception e) {
        User res = User.builder()
                .firstName("dummy first name")
                .lastName("dummy last name")
                .userEmail("dummy user email")
                .build();
//        ApiResponse res = ApiResponse.builder()
//                .message("Some issue has occurred. Please try again after sometime !!.")
//                .success(false)
//                .status(HttpStatus.NOT_FOUND)
//                .build();

        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/deleteUserByUserId/{userId}")
    public ResponseEntity<String> deleteUserByUserId(@PathVariable("userId") String userId) {
        this.userService.deleteUserByUserId(userId);
        return new ResponseEntity<>("User Deleted Successfully!!", HttpStatus.OK);
    }
}
