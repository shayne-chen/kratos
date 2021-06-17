package com.shaw.kratos.common.enums;

/**
 * @author chenxiao
 * @date 2021/6/16 3:39 下午
 */
public enum KratosExceptionEnum {
    SESSION_INVALID("SESSION_INVALID", "session无效"),
    SESSION_MISSED("SESSION_MISSED", "session缺失"),
    USER_PASSWORD_ERROR("USER_PASSWORD_ERROR", "用户名或密码错误")
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
