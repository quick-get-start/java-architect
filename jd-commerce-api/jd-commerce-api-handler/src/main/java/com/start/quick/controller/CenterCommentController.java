package com.start.quick.controller;

import com.start.quick.common.JSONResult;
import com.start.quick.entity.OrderItems;
import com.start.quick.service.CenterCommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CenterCommentController {

    private final CenterCommentService centerCommentService;

    public CenterCommentController(CenterCommentService centerCommentService) {
        this.centerCommentService = centerCommentService;
    }

    @GetMapping("pending")
    public JSONResult<List<OrderItems>> findAllPendingComment(@RequestParam String orderId) {
        List<OrderItems> result = this.centerCommentService.findAllPendingComment(orderId);
        return JSONResult.ok("待评价订单查询成功", result);
    }
}
