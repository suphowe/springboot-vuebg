package com.soft.service;

import com.soft.entity.User;

public interface UserService {

    User getOne(String username);

    User getById(String userId);
}
