package com.start.quick.service.impl;

import com.start.quick.entity.ItemsComments;
import com.start.quick.entity.OrderItems;
import com.start.quick.entity.OrderStatus;
import com.start.quick.entity.Orders;
import com.start.quick.enums.YesOrNo;
import com.start.quick.model.CommentCommonModel;
import com.start.quick.repository.ItemsCommentsRepository;
import com.start.quick.repository.OrderItemsRepository;
import com.start.quick.service.CenterCommentService;
import com.start.quick.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CenterCommentServiceImpl implements CenterCommentService {

    private final OrderItemsRepository orderItemsRepository;
    private final ItemsCommentsRepository itemsCommentsRepository;

    private final OrderService orderService;

    public CenterCommentServiceImpl(OrderItemsRepository orderItemsRepository, ItemsCommentsRepository itemsCommentsRepository, OrderService orderService) {
        this.orderItemsRepository = orderItemsRepository;
        this.itemsCommentsRepository = itemsCommentsRepository;
        this.orderService = orderService;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<OrderItems> findAllPendingComment(String orderId) {
        return this.orderItemsRepository.findAllByOrderId(orderId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<ItemsComments> saveAll(String orderId, String userId, List<CommentCommonModel> data) {
        List<ItemsComments> comments = new ArrayList<>();
        for (CommentCommonModel item : data) {
            ItemsComments comment = new ItemsComments();
            comment.setId(UUID.randomUUID().toString());
            comment.setUserId(userId);
            comment.setItemId(item.getItemId());
            comment.setItemName(item.getItemName());
            comment.setItemSpecId(item.getItemSpecId());
            comment.setSpecName(item.getItemSpecName());
            comment.setCommentLevel(item.getCommentLevel());
            comment.setContent(item.getContent());
            comments.add(comment);
        }
        this.itemsCommentsRepository.saveAll(comments);

        Orders order = this.orderService.findById(orderId);
        order.setIsComment(YesOrNo.YES);

        OrderStatus orderStatus = this.orderService.findOrderStatusByOrderId(orderId);
        orderStatus.setCommentTime(new Date());
        return comments;
    }
}
