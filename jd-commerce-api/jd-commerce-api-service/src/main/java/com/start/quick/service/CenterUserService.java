package com.start.quick.service;

import com.start.quick.entity.Users;

public interface CenterUserService {

    Users findById(String userId);

    Users updateUserInfo(String userId, Users user);

    Users updateUserAvatar(String userId, String avatarUrl);
}
