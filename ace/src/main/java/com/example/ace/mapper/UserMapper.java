package com.example.ace.mapper;

import com.example.ace.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<User> find();
    User findById(int id);

}
