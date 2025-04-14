package com.emission.lcwd.user.service.services;

import com.emission.lcwd.user.service.entities.User;

import java.util.List;

public interface UserService {

    // User operations

    // Create a user
    User saveUser(User user);

    // Get all users
    List<User> getAllUsers();

    // Get a user based on userId
    User getUser(String userId);

    // TODO: delete

    // TODO : update
}
