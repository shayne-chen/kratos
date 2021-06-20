package com.shaw.kratos.common.enums;

/**
 * @author chenxiao
 * @date 2021/6/16 3:39 下午
 */
public enum KratosExceptionEnum {
    SESSION_INVALID("SESSION_INVALID", "session无效"),
    SESSION_MISSED("SESSION_MISSED", "session缺失"),
    PASSWORD_ERROR("PASSWORD_ERROR", "密码错误"),
    USER_NOT_EXIST("USER_NOT_EXIST", "用户不存在"),
    USER_EXISTED("USER_EXISTED", "用户已存在"),
    ;

    KratosExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
