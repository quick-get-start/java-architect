package com.start.quick.service;

import com.start.quick.entity.OrderStatus;
import com.start.quick.entity.Orders;
import com.start.quick.model.OrderSubmitModel;

import java.util.List;

@SuppressWarnings("UnusedReturnValue")
public interface OrderService {

    String create(OrderSubmitModel submitModel);

    OrderStatus updateOrderStatus(String orderId, Integer status);

    Orders findById(String orderId);

    OrderStatus findOrderStatusByOrderId(String orderId);

    List<OrderStatus> closeOrders();
}
