package com.start.quick.controller;

import com.start.quick.common.JSONResult;
import com.start.quick.model.OrderSubmitModel;
import com.start.quick.service.OrderService;
import com.start.quick.utils.CookieUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("orders")
public class OrdersController {

    private final OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("create")
    public JSONResult<String> create(@RequestBody OrderSubmitModel submitModel,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        String orderId = this.orderService.create(submitModel);

        // todo: 整合redis后，需要移除购物车中的已结算商品，并且同步到前端的cookie
        // CookieUtils.setCookie(request, response, "shopcart", "", true);

        return JSONResult.ok("创建订单成功", orderId);
    }
}
