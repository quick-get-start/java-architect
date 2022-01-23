package com.start.quick.service.impl;

import com.start.quick.entity.UserAddress;
import com.start.quick.repository.AddressRepository;
import com.start.quick.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<UserAddress> findAllByUserId(String userId) {
        return this.addressRepository.findAllByUserId(userId);
    }
}
