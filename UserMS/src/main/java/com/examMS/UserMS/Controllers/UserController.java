package com.examMS.UserMS.Controllers;

import com.examMS.UserMS.Entities.User;
import com.examMS.UserMS.Services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

    @CircuitBreaker(name = "getUserCallsRatingAndHotel", fallbackMethod = "getUserCallsRatingAndHotel_fallback")
    @GetMapping("/getUserByUserId/{userId}")
    public ResponseEntity<User> getUserByUserId(@PathVariable("userId") String userId) {
        User res = this.userService.getUserByUserId(userId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<User> getUserCallsRatingAndHotel_fallback(String userId, Exception e) {
        User user = User.builder()
                .firstName("dummy first name")
                .lastName("dummy last name")
                .userEmail("dummy user email")
                .build();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUserByUserId/{userId}")
    public ResponseEntity<String> deleteUserByUserId(@PathVariable("userId") String userId) {
        this.userService.deleteUserByUserId(userId);
        return new ResponseEntity<>("User Deleted Successfully!!", HttpStatus.OK);
    }
}
