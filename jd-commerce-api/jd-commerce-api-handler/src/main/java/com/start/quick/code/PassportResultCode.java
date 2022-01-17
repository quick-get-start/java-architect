package com.start.quick.code;

import com.start.quick.common.ResultCode;

/**
 * 通行证状态码
 */
public class PassportResultCode extends ResultCode {

    /**
     * 用户名已存在
     */
    public static final Integer USERNAME_EXIST = 1;

    /**
     * 弱密码
     */
    public static final Integer WEAK_PASSWORD = 2;

    /**
     * 两次输入密码不一致
     */
    public static final Integer PASSWORD_NOT_MATCH = 3;
}
