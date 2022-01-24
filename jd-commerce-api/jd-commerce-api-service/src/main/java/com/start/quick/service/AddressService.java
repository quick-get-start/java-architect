package com.start.quick.service;

import com.start.quick.entity.UserAddress;
import com.start.quick.model.UserAddressModel;

import java.util.List;

public interface AddressService {

    List<UserAddress> findAllByUserId(String userId);

    UserAddress findById(String addressId);

    void save(UserAddressModel addressModel);

    void update(String id, UserAddressModel addressModel);

    void delete(String id);

    void setDefault(String userId, String addressId);
}
