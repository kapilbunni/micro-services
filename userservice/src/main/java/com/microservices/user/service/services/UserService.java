package com.microservices.user.service.services;

import com.microservices.user.service.entities.User;

import java.util.List;

public interface UserService {

    // All user related services

    //create
    User saveUser(User user);

    //getAll users
    List<User> getAllUsers();

    //get user by Id
    User getUser(String id);

    //delete //update
}
