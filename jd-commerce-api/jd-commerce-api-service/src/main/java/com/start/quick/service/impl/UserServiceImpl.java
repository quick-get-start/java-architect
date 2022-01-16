package com.start.quick.service.impl;

import com.start.quick.repository.UserRepository;
import com.start.quick.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }
}
