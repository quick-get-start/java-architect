package com.start.quick.controller;

import com.github.pagehelper.PageInfo;
import com.start.quick.code.CommentResultCode;
import com.start.quick.common.JSONResult;
import com.start.quick.entity.OrderItems;
import com.start.quick.model.CommentCommonModel;
import com.start.quick.model.CommentModel;
import com.start.quick.service.CenterCommentService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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
    public JSONResult<Void> saveAll(@RequestParam String orderId,
                                    @RequestParam String userId,
                                    @RequestBody List<CommentCommonModel> data) {
        if (ObjectUtils.isEmpty(data)) {
            return JSONResult.build(CommentResultCode.INVALID_REQUEST_PARAM, "评论内容不能为空");
        }

        this.centerCommentService.saveAll(orderId, userId, data);

        return JSONResult.ok("评论成功!");
    }

    @GetMapping
    public JSONResult<PageInfo<CommentModel>> pageAll(@RequestParam String userId,
                                                      @RequestParam(required = false) Integer page,
                                                      @RequestParam(required = false) Integer pageSize) {
        if (StringUtils.isBlank(userId)) {
            return JSONResult.build(CommentResultCode.INVALID_REQUEST_PARAM, "用户不存在");
        }

        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageInfo<CommentModel> result = this.centerCommentService.pageAll(userId, page, pageSize);

        return JSONResult.ok("评论查询成功", result);
    }
}
