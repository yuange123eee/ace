package com.example.ace.service.impl;

import com.example.ace.mapper.UserMapper;
import com.example.ace.pojo.User;
import com.example.ace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserSerivceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> find() {
        return userMapper.find();
    }

}
