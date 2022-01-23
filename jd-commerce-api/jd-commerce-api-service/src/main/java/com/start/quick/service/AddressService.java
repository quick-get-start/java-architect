package com.start.quick.service;

import com.start.quick.entity.UserAddress;
import com.start.quick.model.UserAddressModel;

import java.util.List;

public interface AddressService {

    List<UserAddress> findAllByUserId(String userId);

    void save(UserAddressModel addressModel);

    void update(String id, UserAddressModel addressModel);
}
