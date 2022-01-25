package com.start.quick.repository;

import com.start.quick.entity.OrderStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderStatusRepository extends CrudRepository<OrderStatus, String> {

    List<OrderStatus> findAllByOrderStatus(Integer orderStatus);
}
