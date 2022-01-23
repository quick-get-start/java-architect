package com.start.quick.repository;

import com.start.quick.entity.UserAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<UserAddress, String> {

    List<UserAddress> findAllByUserId(String userId);
}
