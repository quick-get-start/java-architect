package com.start.quick.controller;

import com.start.quick.code.CommentResultCode;
import com.start.quick.common.JSONResult;
import com.start.quick.entity.ItemsComments;
import com.start.quick.entity.OrderItems;
import com.start.quick.http.CommentSubmitRequest;
import com.start.quick.service.CenterCommentService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("saveAll")
    public JSONResult<ItemsComments> saveAll(@RequestParam String userId,
                                             @RequestBody List<CommentSubmitRequest> data) {
        if (ObjectUtils.isEmpty(data)) {
            return JSONResult.build(CommentResultCode.INVALID_REQUEST_PARAM, "评论内容不能为空");
        }

        return JSONResult.ok("ok", null);
    }
}
