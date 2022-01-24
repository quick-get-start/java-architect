package com.start.quick.controller;

import com.start.quick.code.OrderResultCode;
import com.start.quick.common.JSONResult;
import com.start.quick.entity.Orders;
import com.start.quick.enums.CommonOrderStatus;
import com.start.quick.model.OrderSubmitModel;
import com.start.quick.model.WechatPaymentModel;
import com.start.quick.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("pay/{orderId}")
    public JSONResult<Void> pay(@PathVariable String orderId) {
        if (StringUtils.isBlank(orderId)) {
            return JSONResult.build(OrderResultCode.INVALID_REQUEST_PARAM, "订单不存在");
        }

        this.orderService.updateOrderStatus(orderId, CommonOrderStatus.WAIT_DELIVER);
        return JSONResult.ok("支付成功");
    }

    @GetMapping("payment/wechat")
    public JSONResult<WechatPaymentModel> wechatPayment(@RequestParam String orderId) {
        Orders order = this.orderService.findById(orderId);
        WechatPaymentModel result = new WechatPaymentModel();
        result.setAmount(order.getRealPayAmount());
        result.setQrCodeUrl("https://pay.weixin.qq.com/");
        return JSONResult.ok("查询支付二维码成功", result);
    }
}
