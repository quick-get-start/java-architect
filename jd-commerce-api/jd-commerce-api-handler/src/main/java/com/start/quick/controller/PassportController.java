package com.start.quick.controller;

import com.start.quick.code.PassportResultCode;
import com.start.quick.common.JSONResult;
import com.start.quick.entity.Users;
import com.start.quick.http.PassportLoginRequest;
import com.start.quick.http.PassportRegisterRequest;
import com.start.quick.http.UserCommonResponse;
import com.start.quick.model.UserModel;
import com.start.quick.service.UserService;
import com.start.quick.utils.CookieUtils;
import com.start.quick.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @PostMapping("register")
    public JSONResult<UserCommonResponse> register(@RequestBody PassportRegisterRequest registerInfo) {
        String username = registerInfo.getUsername();
        String password = registerInfo.getPassword();
        String confirmPassword = registerInfo.getConfirmPassword();

        if (StringUtils.isBlank(username)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(confirmPassword)) {
            return JSONResult.build(PassportResultCode.INVALID_REQUEST_PARAM, "用户名或密码不能为空");
        }

        boolean exist = this.userService.existsByUsername(username);
        if (exist) {
            return JSONResult.build(PassportResultCode.USERNAME_EXIST, "用户名已存在");
        }

        if (password.length() < 6) {
            return JSONResult.build(PassportResultCode.WEAK_PASSWORD, "密码长度不能少于6");
        }

        if (!StringUtils.equals(password, confirmPassword)) {
            return JSONResult.build(PassportResultCode.PASSWORD_NOT_MATCH, "两次输入密码不一致");
        }

        UserModel userModel = new UserModel();
        userModel.setUsername(username);
        userModel.setPassword(password);
        Users user = this.userService.save(userModel);

        return JSONResult.ok("注册成功", userToResponse(user));
    }

    @PostMapping("login")
    public JSONResult<UserCommonResponse> login(@RequestBody PassportLoginRequest loginInfo,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        String username = loginInfo.getUsername();
        String password = loginInfo.getPassword();

        if (StringUtils.isBlank(username)
                || StringUtils.isBlank(password)) {
            return JSONResult.build(PassportResultCode.INVALID_REQUEST_PARAM, "用户名或密码不能为空");
        }

        Users user = this.userService.checkLoginInfo(username, password);
        if (user == null) {
            return JSONResult.build(PassportResultCode.WRONG_USERNAME_PASSWORD, "用户名或密码不正确");
        }

        UserCommonResponse result = userToResponse(user);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(result), true);

        return JSONResult.ok("登录成功", result);
    }

    private UserCommonResponse userToResponse(Users user) {
        UserCommonResponse response = new UserCommonResponse();
        response.setUsername(user.getUsername());
        response.setNickName(user.getNickName());
        response.setRealName(user.getRealName());
        response.setAvatar(user.getAvatar());
        response.setSex(user.getSex());
        return response;
    }
}
