package com.hd.common.exception;

public class HdBootException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public HdBootException(String message) {
        super(message);
    }

    public HdBootException(Throwable cause) {
        super(cause);
    }

    public HdBootException(String message, Throwable cause) {
        super(message, cause);
    }
}
