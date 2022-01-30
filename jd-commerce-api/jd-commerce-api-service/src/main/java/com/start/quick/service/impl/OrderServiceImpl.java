package com.start.quick.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.start.quick.entity.*;
import com.start.quick.enums.CommonOrderStatus;
import com.start.quick.enums.YesOrNo;
import com.start.quick.mapper.OrderMapper;
import com.start.quick.model.OrderModel;
import com.start.quick.model.OrderStatusModel;
import com.start.quick.model.OrderSubmitModel;
import com.start.quick.model.OrderTrendModel;
import com.start.quick.repository.OrderItemsRepository;
import com.start.quick.repository.OrderStatusRepository;
import com.start.quick.repository.OrdersRepository;
import com.start.quick.service.AddressService;
import com.start.quick.service.ItemService;
import com.start.quick.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderMapper orderMapper;

    private final OrdersRepository ordersRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final OrderStatusRepository orderStatusRepository;

    private final ItemService itemService;
    private final AddressService addressService;

    public OrderServiceImpl(OrderMapper orderMapper, OrdersRepository ordersRepository, OrderItemsRepository orderItemsRepository, OrderStatusRepository orderStatusRepository, ItemService itemService, AddressService addressService) {
        this.orderMapper = orderMapper;
        this.ordersRepository = ordersRepository;
        this.orderItemsRepository = orderItemsRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.itemService = itemService;
        this.addressService = addressService;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String create(OrderSubmitModel submitModel) {
        String userId = submitModel.getUserId();
        String addressId = submitModel.getAddressId();
        String specIds = submitModel.getSpecIds();
        Integer payMethod = submitModel.getPayMethod();
        String leftMsg = submitModel.getLeftMsg();

        Integer postAmount = 0;

        Orders order = new Orders();
        order.setId(UUID.randomUUID().toString());
        order.setUserId(userId);

        UserAddress address = this.addressService.findById(addressId);
        order.setReceiverName(address.getReceiver());
        order.setReceiverMobile(address.getMobile());
        order.setReceiverAddress(String.format("%s,%s,%s,%s", address.getProvince(), address.getCity(), address.getDistrict(), address.getDetail()));

        order.setPostAmount(postAmount);

        order.setPayMethod(payMethod);
        order.setLeftMsg(leftMsg);
        order.setIsComment(YesOrNo.NO);
        order.setIsDelete(YesOrNo.NO);

        int totalAmount = 0;
        int realPayAmount = 0;
        for (String specId : specIds.split(",")) {
            ItemsSpec itemSpec = this.itemService.findItemSpecById(specId);
            // todo: 整合redis后 商品购买的数量从redis的购物车中获取
            int buyCounts = 1;
            totalAmount += itemSpec.getPriceNormal() * buyCounts;
            realPayAmount += itemSpec.getPriceDiscount() * buyCounts;

            String itemId = itemSpec.getItemId();
            Items item = this.itemService.findById(itemId);
            String imageUrl = this.itemService.findItemMainImgById(itemId);

            OrderItems subOrder = new OrderItems();
            subOrder.setId(UUID.randomUUID().toString());
            subOrder.setOrderId(order.getId());
            subOrder.setItemId(itemId);
            subOrder.setItemName(item.getItemName());
            subOrder.setItemImg(imageUrl);
            subOrder.setBuyCounts(buyCounts);
            subOrder.setItemSpecId(specId);
            subOrder.setItemSpecName(itemSpec.getName());
            subOrder.setPrice(itemSpec.getPriceDiscount());
            this.orderItemsRepository.save(subOrder);

            this.itemService.decreaseItemSpecStock(specId, buyCounts);
        }

        order.setTotalAmount(totalAmount);
        order.setRealPayAmount(realPayAmount);
        this.ordersRepository.save(order);

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(order.getId());
        orderStatus.setOrderStatus(CommonOrderStatus.WAIT_PAY);
        orderStatus.setCreateTime(new Date());
        this.orderStatusRepository.save(orderStatus);

        return order.getId();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public OrderStatus updatePayStatus(String orderId) {
        OrderStatus orderStatus = this.findOrderStatusByOrderId(orderId);
        orderStatus.setOrderStatus(CommonOrderStatus.WAIT_DELIVER);
        orderStatus.setPayTime(new Date());
        return orderStatus;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public OrderStatus updateDeliverStatus(String orderId) {
        OrderStatus orderStatus = this.findOrderStatusByOrderId(orderId);
        orderStatus.setOrderStatus(CommonOrderStatus.WAIT_RECEIVE);
        orderStatus.setDeliverTime(new Date());
        return orderStatus;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public OrderStatus updateReceiveStatus(String orderId) {
        OrderStatus orderStatus = this.findOrderStatusByOrderId(orderId);
        orderStatus.setOrderStatus(CommonOrderStatus.SUCCESS);
        orderStatus.setSuccessTime(new Date());
        return orderStatus;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Orders findById(String orderId) {
        return this.ordersRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public OrderStatus findOrderStatusByOrderId(String orderId) {
        return this.orderStatusRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<OrderStatus> closeOrders() {
        // 查询所有未付款订单，判断时间是否超时(1天)，超时则关闭交易
        List<OrderStatus> list = this.orderStatusRepository.findAllByOrderStatus(CommonOrderStatus.WAIT_PAY);
        for (OrderStatus orderStatus : list) {
            Date createTime = orderStatus.getCreateTime();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            if (createTime.before(calendar.getTime())) {
                logger.info("订单已超时1天未付款，关闭订单 {}", orderStatus.getOrderId());
                orderStatus.setOrderStatus(CommonOrderStatus.CLOSE);
                orderStatus.setCloseTime(new Date());
            }
        }
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PageInfo<OrderModel> pageAll(String userId, Integer status, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<OrderModel> content = this.orderMapper.findAll(userId, status);
        return new PageInfo<>(content);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public OrderStatusModel countByStatus(String userId) {
        Integer waitPayCount = this.orderMapper.countByStatus(userId, CommonOrderStatus.WAIT_PAY, null);
        Integer waitDeliverCount = this.orderMapper.countByStatus(userId, CommonOrderStatus.WAIT_DELIVER, null);
        Integer waitReceiveCount = this.orderMapper.countByStatus(userId, CommonOrderStatus.WAIT_RECEIVE, null);
        Integer waitCommentCount = this.orderMapper.countByStatus(userId, CommonOrderStatus.SUCCESS, YesOrNo.NO);

        return new OrderStatusModel(waitPayCount, waitDeliverCount, waitReceiveCount, waitCommentCount);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PageInfo<OrderTrendModel> orderTrend(String userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<OrderTrendModel> content = this.orderMapper.orderTrend(userId);
        return new PageInfo<>(content);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteById(String orderId) {
        Orders order = this.findById(orderId);
        order.setIsDelete(YesOrNo.YES);
    }
}
