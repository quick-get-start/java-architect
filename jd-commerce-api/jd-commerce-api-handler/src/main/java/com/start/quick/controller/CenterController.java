package com.start.quick.controller;

import com.github.pagehelper.PageInfo;
import com.start.quick.code.ItemResultCode;
import com.start.quick.common.JSONResult;
import com.start.quick.entity.Users;
import com.start.quick.http.UserCommonResponse;
import com.start.quick.model.OrderStatusModel;
import com.start.quick.model.OrderTrendModel;
import com.start.quick.service.CenterUserService;
import com.start.quick.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("center")
public class CenterController {

    private final CenterUserService centerUserService;
    private final OrderService orderService;

    public CenterController(CenterUserService centerUserService, OrderService orderService) {
        this.centerUserService = centerUserService;
        this.orderService = orderService;
    }

    @GetMapping("userInfo")
    public JSONResult<UserCommonResponse> userInfo(@RequestParam String userId) {
        Users user = this.centerUserService.findById(userId);
        return JSONResult.ok("查询用户信息成功", UserCommonResponse.userToResponse(user));
    }

    @GetMapping("countStatus")
    public JSONResult<OrderStatusModel> countStatus(@RequestParam String userId) {
        OrderStatusModel result = this.orderService.countByStatus(userId);
        return JSONResult.ok("统计成功", result);
    }

    @GetMapping("trend")
    public JSONResult<PageInfo<OrderTrendModel>> trend(@RequestParam String userId,
                                             @RequestParam(required = false) Integer page,
                                             @RequestParam(required = false) Integer pageSize) {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageInfo<OrderTrendModel> result = this.orderService.orderTrend(userId, page, pageSize);
        return JSONResult.ok("查询订单动向成功", result);
    }
}
