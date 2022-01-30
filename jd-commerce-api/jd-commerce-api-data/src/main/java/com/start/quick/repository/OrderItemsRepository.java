package com.start.quick.repository;

import com.start.quick.entity.OrderItems;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderItemsRepository extends CrudRepository<OrderItems, String> {

    List<OrderItems> findAllByOrderId(String orderId);
}
