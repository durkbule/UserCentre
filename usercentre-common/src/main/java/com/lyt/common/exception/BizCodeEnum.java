package com.lyt.common.exception;

public enum BizCodeEnum {

    UNKNOW_ERROR(10001,"未知错误，请联系管理员"),
    DATE_VALIDATE_ERROR(10002, "数据校验出现问题"),
    SAME_USER_NAME(20001,"用户名重复"),
    USER_NAME_OR_PASSWORD_ERROR(20002,"用户名或密码错误"),
    CAPTCHA_ERROR(20003,"验证码错误"),
    USER_LOCKED(30001,"账号已被锁定"),
    USER_EXIST(30002,"账号已存在，请直接登录"),

    ;


    private int code;
    private String msg;

    BizCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
