package com.start.quick.repository;

import com.start.quick.entity.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, String> {
}
