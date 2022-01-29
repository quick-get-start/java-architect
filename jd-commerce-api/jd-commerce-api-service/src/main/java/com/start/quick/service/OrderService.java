package com.start.quick.service;

import com.github.pagehelper.PageInfo;
import com.start.quick.entity.OrderStatus;
import com.start.quick.entity.Orders;
import com.start.quick.model.OrderModel;
import com.start.quick.model.OrderSubmitModel;

import java.util.List;

@SuppressWarnings("UnusedReturnValue")
public interface OrderService {

    String create(OrderSubmitModel submitModel);

    OrderStatus updatePayStatus(String orderId);

    Orders findById(String orderId);

    OrderStatus findOrderStatusByOrderId(String orderId);

    List<OrderStatus> closeOrders();

    PageInfo<OrderModel> pageAll(String userId, Integer status, Integer page, Integer pageSize);
}
