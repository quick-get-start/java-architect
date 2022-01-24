package com.start.quick.service;

import com.start.quick.entity.Orders;
import com.start.quick.model.OrderSubmitModel;

public interface OrderService {

    String create(OrderSubmitModel submitModel);

    void updateOrderStatus(String orderId, Integer status);

    Orders findById(String orderId);
}
