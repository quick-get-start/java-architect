package com.start.quick.service;

import com.start.quick.entity.UserAddress;

import java.util.List;

public interface AddressService {

    List<UserAddress> findAllByUserId(String userId);
}
