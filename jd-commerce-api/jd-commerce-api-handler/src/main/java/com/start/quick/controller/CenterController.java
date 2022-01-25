package com.start.quick.controller;

import com.start.quick.common.JSONResult;
import com.start.quick.entity.Users;
import com.start.quick.http.UserCommonResponse;
import com.start.quick.service.CenterUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("center")
public class CenterController {

    private final CenterUserService centerUserService;

    public CenterController(CenterUserService centerUserService) {
        this.centerUserService = centerUserService;
    }

    @GetMapping("userInfo")
    public JSONResult<UserCommonResponse> userInfo(@RequestParam String userId) {
        Users user = this.centerUserService.findById(userId);
        return JSONResult.ok("查询用户信息成功", UserCommonResponse.userToResponse(user));
    }
}
