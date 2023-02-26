package com.examMS.UserMS.Services.ServiceImplementation;

import com.examMS.UserMS.Entities.User;
import com.examMS.UserMS.Exceptions.ResourceNotFoundException;
import com.examMS.UserMS.Repositories.UserRepository;
import com.examMS.UserMS.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
        return this.userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("No such user present with user id : " + userId)
        );
    }

    @Override
    public void deleteUserByUserId(String userId) {
        this.getUserByUserId(userId);
        this.userRepository.deleteById(userId);
    }
}
