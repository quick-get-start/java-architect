package com.start.quick.service.impl;

import com.start.quick.entity.Users;
import com.start.quick.enums.Sex;
import com.start.quick.model.UserModel;
import com.start.quick.repository.UserRepository;
import com.start.quick.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    public static final String DEFAULT_USER_AVATAR = "xxx.jpg";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users save(UserModel userModel) {
        Users user = new Users();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(userModel.getUsername());
        user.setPassword(this.passwordEncoder.encode(userModel.getPassword()));
        user.setNickName(userModel.getUsername());
        user.setAvatar(DEFAULT_USER_AVATAR);
        user.setBirthday(Calendar.getInstance());
        user.setSex(Sex.SECRET);
        return this.userRepository.save(user);
    }
}
