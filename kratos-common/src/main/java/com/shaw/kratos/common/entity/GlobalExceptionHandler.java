package com.shaw.kratos.common.entity;

import com.shaw.kratos.common.enums.ResponseStatusEnum;
import com.shaw.kratos.common.exceptions.KratosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseStatus handleException(Exception e) {
        return ResponseStatus.builder()
                .code(ResponseStatusEnum.SYSTEM_ERROR.getCode())
                .message(e.getMessage())
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

}
