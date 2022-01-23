package com.start.quick.service.impl;

import com.start.quick.entity.UserAddress;
import com.start.quick.model.UserAddressModel;
import com.start.quick.repository.AddressRepository;
import com.start.quick.service.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserAddress> findAllByUserId(String userId) {
        return this.addressRepository.findAllByUserId(userId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save(UserAddressModel addressModel) {
        int isDefault = 0;
        List<UserAddress> addresses = this.findAllByUserId(addressModel.getUserId());
        if (CollectionUtils.isEmpty(addresses)) {
            isDefault = 1;
        }

        UserAddress address = new UserAddress();
        BeanUtils.copyProperties(addressModel, address);
        address.setId(UUID.randomUUID().toString());
        address.setIsDefault(isDefault);
        this.addressRepository.save(address);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(String id, UserAddressModel addressModel) {

    }
}
