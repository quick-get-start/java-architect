package com.start.quick.http;

import com.start.quick.entity.Users;

import java.util.Calendar;
import java.util.Date;

public class UserCommonResponse {

    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别 0:女 1:男 2:保密
     */
    private Integer sex;

    /**
     * 生日
     */
    private Calendar birthday;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱地址
     */
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static UserCommonResponse userToResponse(Users user) {
        UserCommonResponse response = new UserCommonResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setNickName(user.getNickName());
        response.setRealName(user.getRealName());
        response.setAvatar(user.getAvatar());
        response.setSex(user.getSex());
        response.setBirthday(user.getBirthday());
        response.setMobile(user.getMobile());
        response.setEmail(user.getEmail());
        return response;
    }
}
