package com.start.quick.service;

import com.github.pagehelper.PageInfo;
import com.start.quick.entity.OrderStatus;
import com.start.quick.entity.Orders;
import com.start.quick.model.OrderModel;
import com.start.quick.model.OrderStatusModel;
import com.start.quick.model.OrderSubmitModel;
import com.start.quick.model.OrderTrendModel;

import java.util.List;

@SuppressWarnings("UnusedReturnValue")
public interface OrderService {

    String create(OrderSubmitModel submitModel);

    OrderStatus updatePayStatus(String orderId);

    OrderStatus updateDeliverStatus(String orderId);

    OrderStatus updateReceiveStatus(String orderId);

    Orders findById(String orderId);

    OrderStatus findOrderStatusByOrderId(String orderId);

    List<OrderStatus> closeOrders();

    PageInfo<OrderModel> pageAll(String userId, Integer status, Integer page, Integer pageSize);

    OrderStatusModel countByStatus(String userId);

    PageInfo<OrderTrendModel> orderTrend(String userId, Integer page, Integer pageSize);

    void deleteById(String orderId);
}
