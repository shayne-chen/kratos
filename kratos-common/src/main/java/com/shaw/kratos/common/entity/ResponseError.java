package com.shaw.kratos.common.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author shaw
 * @date 2021/6/16
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
