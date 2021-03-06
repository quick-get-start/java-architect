package com.start.quick.service.impl;

import com.start.quick.entity.Users;
import com.start.quick.repository.UserRepository;
import com.start.quick.service.CenterUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class CenterUserServiceImpl implements CenterUserService {

    private final UserRepository userRepository;

    public CenterUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users findById(String userId) {
        return this.userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users updateUserInfo(String userId, Users user) {
        Users entity = this.findById(userId);
        entity.setNickName(user.getNickName());
        entity.setRealName(user.getRealName());
        entity.setSex(user.getSex());
        entity.setBirthday(user.getBirthday());
        entity.setMobile(user.getMobile());
        entity.setEmail(user.getEmail());
        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users updateUserAvatar(String userId, String avatarUrl) {
        Users entity = this.findById(userId);
        entity.setAvatar(avatarUrl);
        return entity;
    }
}
