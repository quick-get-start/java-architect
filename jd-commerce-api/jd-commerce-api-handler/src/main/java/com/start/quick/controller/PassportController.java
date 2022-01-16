package com.start.quick.controller;

import com.start.quick.code.PassportResultCode;
import com.start.quick.common.JSONResult;
import com.start.quick.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通行证控制器
 */
@RestController
@RequestMapping("passport")
public class PassportController {

    private final UserService userService;

    public PassportController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("existsByUsername")
    public JSONResult<Void> existsByUsername(@RequestParam String username) {
        if (StringUtils.isBlank(username)) {
            return JSONResult.build(PassportResultCode.INVALID_REQUEST_PARAM, "用户名不能为空");
        }

        boolean exist = this.userService.existsByUsername(username);
        if (exist) {
            return JSONResult.build(PassportResultCode.USERNAME_EXIST, "用户名已存在");
        }

        return JSONResult.ok("用户名可用");
    }
}
