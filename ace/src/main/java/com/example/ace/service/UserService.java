package com.example.ace.service;

import com.example.ace.pojo.User;

import java.util.List;

public interface UserService {
    List<User> find();
    User findById(int id);
}
