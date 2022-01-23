package com.start.quick.controller;

import com.start.quick.common.JSONResult;
import com.start.quick.model.OrderSubmitModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrdersController {

    @PostMapping("create")
    public JSONResult<Void> create(@RequestBody OrderSubmitModel submitModel) {
        return JSONResult.ok("创建订单成功");
    }
}
