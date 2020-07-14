package com.soft.service.impl;

import com.soft.mapper.UserMapper;
import com.soft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.soft.entity.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getOne(String username){
        return userMapper.selectByUserName(username);
    }

    @Override
    public User getById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

}
