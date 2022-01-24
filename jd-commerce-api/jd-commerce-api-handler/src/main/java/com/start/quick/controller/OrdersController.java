package com.start.quick.controller;

import com.start.quick.code.OrderResultCode;
import com.start.quick.common.JSONResult;
import com.start.quick.model.OrderSubmitModel;
import com.start.quick.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrdersController {

    private final OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("create")
    public JSONResult<Void> create(@RequestBody OrderSubmitModel submitModel) {
        try {
            this.orderService.create(submitModel);
        } catch (RuntimeException exception) {
            return JSONResult.build(OrderResultCode.ORDER_ERROR, exception.getMessage());
        }

        return JSONResult.ok("创建订单成功");
    }
}
