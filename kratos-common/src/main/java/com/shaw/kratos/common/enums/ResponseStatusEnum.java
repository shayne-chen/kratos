package com.shaw.kratos.common.enums;

public enum ResponseStatusEnum {
    SUCCESS(0, "成功"),
    FAILED(-1, "失败"),
    BUSINESS_ERROR(-2, "业务错误"),
    SESSION_LOSS(1001, "session丢失"),
    SESSION_INVALID(1002, "session无效"),
    USER_NOT_EXIST(1003, "用户不存在"),
    PASSWORD_ERROR(1004, "密码错误"),
    SYSTEM_ERROR(2001, "系统错误"),
    ;

    private Integer code;
    private String message;

    ResponseStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
