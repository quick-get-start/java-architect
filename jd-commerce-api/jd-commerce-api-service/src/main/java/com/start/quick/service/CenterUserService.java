package com.start.quick.service;

import com.start.quick.entity.Users;

public interface CenterUserService {

    Users findById(String userId);

    void updateUserInfo(String userId, Users user);
}
