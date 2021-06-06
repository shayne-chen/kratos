package com.shaw.kratos.common.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseStatus {

    private Integer code;
    private String message;
    private boolean success;
    private Long t;
}
