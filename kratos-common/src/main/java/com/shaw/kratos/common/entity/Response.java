package com.shaw.kratos.common.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Response<T> {

    private Integer code;
    private String message;
    private Boolean success;
    private T data;
    private Long t;
}
