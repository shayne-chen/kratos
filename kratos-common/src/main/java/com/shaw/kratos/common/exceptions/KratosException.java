package com.shaw.kratos.common.exceptions;

public class KratosException extends RuntimeException {

    public KratosException() {
        super();
    }

    public KratosException(String message) {
        super(message);
    }

    public KratosException(String message, Throwable cause) {
        super(message, cause);
    }

    public KratosException(Throwable cause) {
        super(cause);
    }
}
