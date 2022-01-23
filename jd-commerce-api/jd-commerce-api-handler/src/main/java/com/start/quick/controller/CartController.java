package com.start.quick.controller;

import com.start.quick.code.CartResultCode;
import com.start.quick.common.JSONResult;
import com.start.quick.http.CartRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("cart")
public class CartController {

    @PostMapping("add")
    public JSONResult<Void> add(@RequestParam String userId,
                                @RequestBody CartRequest cartRequest,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        if (StringUtils.isBlank(userId)) {
            return JSONResult.build(CartResultCode.INVALID_REQUEST_PARAM, "用户不存在");
        }

        // todo: 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存

        return JSONResult.ok("添加到购物车成功");
    }

    @PostMapping("del")
    public JSONResult<Void> add(@RequestParam String userId,
                                @RequestParam String specId,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        if (StringUtils.isBlank(userId)) {
            return JSONResult.build(CartResultCode.INVALID_REQUEST_PARAM, "用户不存在");
        }

        if (StringUtils.isBlank(specId)) {
            return JSONResult.build(CartResultCode.INVALID_REQUEST_PARAM, "商品不存在");
        }

        // todo: 用户在页面删除购物车中的数据，如果用户在登录的情况下，需要同步删除购物车到redis缓存

        return JSONResult.ok("购物车删除商品成功");
    }
}
