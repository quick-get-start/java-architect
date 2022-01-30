package com.start.quick.service;

import com.github.pagehelper.PageInfo;
import com.start.quick.entity.ItemsComments;
import com.start.quick.entity.OrderItems;
import com.start.quick.model.CommentCommonModel;
import com.start.quick.model.CommentModel;

import java.util.List;

@SuppressWarnings("UnusedReturnValue")
public interface CenterCommentService {

    List<OrderItems> findAllPendingComment(String orderId);

    List<ItemsComments> saveAll(String orderId, String userId, List<CommentCommonModel> data);

    PageInfo<CommentModel> pageAll(String userId, Integer page, Integer pageSize);
}
