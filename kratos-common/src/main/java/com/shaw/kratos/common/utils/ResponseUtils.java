package com.shaw.kratos.common.utils;

import com.shaw.kratos.common.entity.Response;
import com.shaw.kratos.common.entity.ResponseStatus;
import com.shaw.kratos.common.enums.ResponseStatusEnum;

public class ResponseUtils {

    public static <T> Response buildSuccessResponse(T data) {
        return Response.builder()
                .code(ResponseStatusEnum.SUCCESS.getCode())
                .message(ResponseStatusEnum.SUCCESS.getMessage())
                .success(true)
                .t(System.currentTimeMillis())
                .data(data).build();
    }

    public static ResponseStatus success() {
        return ResponseStatus.builder()
                .code(ResponseStatusEnum.SUCCESS.getCode())
                .message(ResponseStatusEnum.SUCCESS.getMessage())
                .success(true)
                .t(System.currentTimeMillis()).build();
    }

}
