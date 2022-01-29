package com.start.quick.controller;

import com.start.quick.code.CenterUserResultCode;
import com.start.quick.common.JSONResult;
import com.start.quick.entity.Users;
import com.start.quick.http.UserCommonResponse;
import com.start.quick.properties.FileUploadProperties;
import com.start.quick.service.CenterUserService;
import com.start.quick.utils.CookieUtils;
import com.start.quick.utils.JsonUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("userInfo")
public class CenterUserController {

    private final CenterUserService centerUserService;
    private final FileUploadProperties fileUploadProperties;

    public CenterUserController(CenterUserService centerUserService, FileUploadProperties fileUploadProperties) {
        this.centerUserService = centerUserService;
        this.fileUploadProperties = fileUploadProperties;
    }

    @PostMapping("updateAvatar")
    public JSONResult<Void> updateAvatar(@RequestParam String userId,
                                         MultipartFile file) {
        if (ObjectUtils.isEmpty(file)) {
            return JSONResult.build(CenterUserResultCode.INVALID_REQUEST_PARAM, "文件不能为空!");
        }

        String fileName = file.getOriginalFilename();
        if (StringUtils.isNotBlank(fileName)) {
            String[] arr = fileName.split("\\.");
            String suffix = arr[arr.length - 1];
            String name = "face-" + userId + "." + suffix;

            String filePath = fileUploadProperties.getImageUserLocation() + File.separator + userId + File.separator + name;
            File avatar = new File(filePath);
            if (avatar.getParentFile() != null) {
                //noinspection ResultOfMethodCallIgnored
                avatar.getParentFile().mkdirs();
            }

            try (FileOutputStream outputStream = new FileOutputStream(avatar)) {
                InputStream inputStream = file.getInputStream();
                IOUtils.copy(inputStream, outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return JSONResult.ok("头像更新成功");
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
