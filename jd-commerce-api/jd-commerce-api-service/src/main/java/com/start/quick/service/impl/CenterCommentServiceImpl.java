package com.start.quick.service.impl;

import com.start.quick.entity.OrderItems;
import com.start.quick.repository.ItemsCommentsRepository;
import com.start.quick.repository.OrderItemsRepository;
import com.start.quick.service.CenterCommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CenterCommentServiceImpl implements CenterCommentService {

    private final OrderItemsRepository orderItemsRepository;
    private final ItemsCommentsRepository itemsCommentsRepository;

    public CenterCommentServiceImpl(OrderItemsRepository orderItemsRepository, ItemsCommentsRepository itemsCommentsRepository) {
        this.orderItemsRepository = orderItemsRepository;
        this.itemsCommentsRepository = itemsCommentsRepository;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<OrderItems> findAllPendingComment(String orderId) {
        return this.orderItemsRepository.findAllByOrderId(orderId);
    }
}
