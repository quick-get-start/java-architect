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
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    public static final String DEFAULT_USER_AVATAR = "https://wx3.sinaimg.cn/orj360/001l3UC8gy1gy5gbleci2j637k2eo1ky02.jpg";

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

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users checkLoginInfo(String username, String password) {
        Users user = this.userRepository.findByUsername(username);
        if (user != null && this.passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}
