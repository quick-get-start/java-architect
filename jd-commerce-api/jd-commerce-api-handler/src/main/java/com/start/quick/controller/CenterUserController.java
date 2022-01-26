package com.start.quick.controller;

import com.start.quick.common.JSONResult;
import com.start.quick.entity.Users;
import com.start.quick.http.UserCommonResponse;
import com.start.quick.service.CenterUserService;
import com.start.quick.utils.CookieUtils;
import com.start.quick.utils.JsonUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("userInfo")
public class CenterUserController {

    private final CenterUserService centerUserService;

    public CenterUserController(CenterUserService centerUserService) {
        this.centerUserService = centerUserService;
    }

    @PostMapping("update")
    public JSONResult<Void> updateUserInfo(@RequestParam String userId,
                                           @RequestBody Users user,
                                           HttpServletRequest request,
                                           HttpServletResponse response) {
        this.centerUserService.updateUserInfo(userId, user);

        UserCommonResponse result = UserCommonResponse.userToResponse(user);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(result), true);

        // todo 增加令牌token 整合redis分布式会话

        return JSONResult.ok("用户信息更新成功");
    }
}
