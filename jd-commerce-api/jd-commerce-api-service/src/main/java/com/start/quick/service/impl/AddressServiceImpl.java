package com.start.quick.service.impl;

import com.start.quick.entity.UserAddress;
import com.start.quick.enums.YesOrNo;
import com.start.quick.model.UserAddressModel;
import com.start.quick.repository.AddressRepository;
import com.start.quick.service.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
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

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public UserAddress findById(String addressId) {
        return this.addressRepository.findById(addressId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public UserAddress save(UserAddressModel addressModel) {
        int isDefault = YesOrNo.NO;
        List<UserAddress> addresses = this.findAllByUserId(addressModel.getUserId());
        if (CollectionUtils.isEmpty(addresses)) {
            isDefault = YesOrNo.YES;
        }

        UserAddress address = new UserAddress();
        BeanUtils.copyProperties(addressModel, address);
        address.setId(UUID.randomUUID().toString());
        address.setIsDefault(isDefault);
        this.addressRepository.save(address);
        return address;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public UserAddress update(String id, UserAddressModel addressModel) {
        UserAddress entity = this.addressRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        BeanUtils.copyProperties(addressModel, entity);
        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public UserAddress setDefault(String userId, String addressId) {
        List<UserAddress> addresses = this.addressRepository.findAllByUserIdAndIsDefault(userId, YesOrNo.YES);
        for (UserAddress address : addresses) {
            address.setIsDefault(YesOrNo.NO);
        }
        UserAddress address = this.addressRepository.findById(addressId).orElseThrow(EntityNotFoundException::new);
        address.setIsDefault(YesOrNo.YES);
        return address;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteById(String id) {
        this.addressRepository.deleteById(id);
    }
}
