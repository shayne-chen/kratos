package com.shaw.kratos.common.entity;

import com.shaw.kratos.common.enums.ResponseStatusEnum;
import com.shaw.kratos.common.exceptions.BusinessException;
import com.shaw.kratos.common.exceptions.KratosException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseStatus handleException(Exception e) {
        e.printStackTrace();
        return ResponseStatus.builder()
                .code(ResponseStatusEnum.SYSTEM_ERROR.getCode())
                .message(e.toString())
                .success(false)
                .t(System.currentTimeMillis()).build();
    }

    @ExceptionHandler(KratosException.class)
    @ResponseBody
    ResponseStatus handleKratosException(KratosException e) {
        return ResponseStatus.builder()
                .code(ResponseStatusEnum.SYSTEM_ERROR.getCode())
                .message(e.getMessage())
                .success(false)
                .t(System.currentTimeMillis()).build();
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    ResponseError handleBusinessException(BusinessException e) {
        return ResponseError.builder()
                .code(ResponseStatusEnum.BUSINESS_ERROR.getCode())
                .success(false)
                .errorCode(e.getErrorCode())
                .errorMessage(e.getErrorMessage())
                .t(System.currentTimeMillis()).build();
    }

}
