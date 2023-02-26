package com.examMS.UserMS.Services;

import com.examMS.UserMS.Entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public User createUser(User user);

    public List<User> getAllUsers();

    public User getUserByUserId(String userId);

    public void deleteUserByUserId(String userId);
}
