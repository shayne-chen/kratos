package com.shaw.kratos.common.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author chenxiao
 * @date 2021/6/16 4:24 下午
 */
@Data
@Builder
public class ResponseError {
    private Integer code;
    private boolean success;
    private String errorCode;
    private String errorMessage;
    private Long t;
}
