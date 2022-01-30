package com.start.quick.service;

import com.start.quick.entity.ItemsComments;
import com.start.quick.entity.OrderItems;
import com.start.quick.model.CommentCommonModel;

import java.util.List;

public interface CenterCommentService {

    List<OrderItems> findAllPendingComment(String orderId);

    List<ItemsComments> saveAll(String orderId, String userId, List<CommentCommonModel> data);
}
