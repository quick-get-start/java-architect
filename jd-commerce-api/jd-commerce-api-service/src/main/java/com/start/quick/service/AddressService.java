package com.start.quick.service;

import com.start.quick.entity.UserAddress;
import com.start.quick.model.UserAddressModel;

import java.util.List;

@SuppressWarnings("UnusedReturnValue")
public interface AddressService {

    List<UserAddress> findAllByUserId(String userId);

    UserAddress findById(String addressId);

    UserAddress save(UserAddressModel addressModel);

    UserAddress update(String id, UserAddressModel addressModel);

    UserAddress setDefault(String userId, String addressId);

    void deleteById(String id);
}
