package com.start.quick.repository;

import com.start.quick.entity.OrderItems;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemsRepository extends CrudRepository<OrderItems, String> {
}
