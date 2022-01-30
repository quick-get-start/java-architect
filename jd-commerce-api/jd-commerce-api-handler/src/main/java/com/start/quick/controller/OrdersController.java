package com.start.quick.controller;

import com.github.pagehelper.PageInfo;
import com.start.quick.code.ItemResultCode;
import com.start.quick.code.OrderResultCode;
import com.start.quick.common.JSONResult;
import com.start.quick.entity.OrderStatus;
import com.start.quick.entity.Orders;
import com.start.quick.model.OrderModel;
import com.start.quick.model.OrderSubmitModel;
import com.start.quick.model.WechatPaymentModel;
import com.start.quick.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orders")
public class OrdersController {

    private final OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("create")
    public JSONResult<String> create(@RequestBody OrderSubmitModel submitModel) {
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

        this.orderService.updatePayStatus(orderId);
        return JSONResult.ok("支付成功");
    }

    @PostMapping("deliver/{orderId}")
    public JSONResult<Void> deliver(@PathVariable String orderId) {
        if (StringUtils.isBlank(orderId)) {
            return JSONResult.build(OrderResultCode.INVALID_REQUEST_PARAM, "订单不存在");
        }

        this.orderService.updateDeliverStatus(orderId);
        return JSONResult.ok("发货成功");
    }

    @GetMapping("payment/wechat")
    public JSONResult<WechatPaymentModel> wechatPayment(@RequestParam String orderId) {
        Orders order = this.orderService.findById(orderId);
        WechatPaymentModel result = new WechatPaymentModel();
        result.setAmount(order.getRealPayAmount());
        result.setQrCodeUrl("https://pay.weixin.qq.com/");
        return JSONResult.ok("查询支付二维码成功", result);
    }

    @GetMapping("payment/alipay")
    public JSONResult<String> alipayPayment() {
        return JSONResult.ok("支付宝支付脚本查询成功", "<form method=\"get\" action=\"https://opendocs.alipay.com/open\"></form>\n" +
                "<script type=\"text/javascript\">document.forms[0].submit();</script>");
    }

    @GetMapping("status")
    public JSONResult<OrderStatus> status(@RequestParam String orderId) {
        OrderStatus result = this.orderService.findOrderStatusByOrderId(orderId);
        return JSONResult.ok("查询订单状态成功", result);
    }

    @PostMapping("confirmReceive")
    public JSONResult<Void> confirmReceive(@RequestParam String orderId) {
        if (StringUtils.isBlank(orderId)) {
            return JSONResult.build(OrderResultCode.INVALID_REQUEST_PARAM, "订单不存在");
        }

        this.orderService.updateReceiveStatus(orderId);
        return JSONResult.ok("收货成功");
    }

    @PostMapping("delete")
    public JSONResult<Void> delete(@RequestParam String orderId) {
        if (StringUtils.isBlank(orderId)) {
            return JSONResult.build(OrderResultCode.INVALID_REQUEST_PARAM, "订单不存在");
        }

        this.orderService.deleteById(orderId);
        return JSONResult.ok("删除成功");
    }

    @GetMapping("pageAll")
    public JSONResult<PageInfo<OrderModel>> pageAll(@RequestParam String userId,
                                          @RequestParam(required = false) Integer status,
                                          @RequestParam(required = false) Integer page,
                                          @RequestParam(required = false) Integer pageSize) {
        if (StringUtils.isBlank(userId)) {
            return JSONResult.build(ItemResultCode.INVALID_REQUEST_PARAM, "用户不存在");
        }

        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageInfo<OrderModel> result = this.orderService.pageAll(userId, status, page, pageSize);

        return JSONResult.ok("订单信息查询成功", result);
    }
}
