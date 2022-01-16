package com.start.quick.common;

/**
 * JSON 响应
 * @param <T> 响应数据类型
 */
public class JSONResult<T> {

    /**
     * 业务状态
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public static <T> JSONResult<T> build(Integer code, String msg) {
        return new JSONResult<>(code, msg);
    }

    public static <T> JSONResult<T> build(Integer code, String msg, T data) {
        return new JSONResult<>(code, msg, data);
    }

    public static <T> JSONResult<T> ok(String msg) {
        return new JSONResult<>(ResultCode.SUCCESS, msg);
    }

    public static <T> JSONResult<T> ok(String msg, T data) {
        return new JSONResult<>(ResultCode.SUCCESS, msg, data);
    }

    public JSONResult() {
    }

    public JSONResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JSONResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
