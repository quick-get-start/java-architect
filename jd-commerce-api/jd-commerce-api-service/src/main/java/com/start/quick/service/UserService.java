package com.start.quick.service;

import com.start.quick.entity.Users;
import com.start.quick.model.UserModel;

public interface UserService {

    boolean existsByUsername(String username);

    Users save(UserModel userModel);
}
