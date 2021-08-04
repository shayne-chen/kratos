package com.shaw.kratos.common.exceptions;

import com.shaw.kratos.common.enums.KratosExceptionEnum;

/**
 * @author shaw
 * @date 2021/6/16
 */
public class BusinessException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    public BusinessException() { super();}

    public BusinessException(String message) { super(message);}

    public BusinessException(Throwable cause) { super(cause);}

    public BusinessException(String message, Throwable cause) { super(message, cause);}

    public BusinessException(KratosExceptionEnum kratosExceptionEnum) {
        super(kratosExceptionEnum.getMessage());
        this.errorCode = kratosExceptionEnum.getCode();
        this.errorMessage = kratosExceptionEnum.getMessage();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
