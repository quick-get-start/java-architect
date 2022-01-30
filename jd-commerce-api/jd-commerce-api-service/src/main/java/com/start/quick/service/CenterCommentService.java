package com.start.quick.service;

import com.start.quick.entity.OrderItems;

import java.util.List;

public interface CenterCommentService {

    List<OrderItems> findAllPendingComment(String orderId);
}
