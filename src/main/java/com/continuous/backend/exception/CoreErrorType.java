package com.continuous.backend.exception;

import org.springframework.http.HttpStatus;

public enum CoreErrorType {

    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", "요청한 리소스를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    INVALID_INPUT("INVALID_INPUT", "제공된 입력이 유효하지 않습니다.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("UNAUTHORIZED", "사용자가 인증되지 않았습니다.", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("FORBIDDEN", "이 리소스에 대한 접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    CONFLICT("CONFLICT", "리소스의 현재 상태와 충돌이 발생했습니다.", HttpStatus.CONFLICT),
    DATA_INTEGRITY_VIOLATION("DATA_INTEGRITY_VIOLATION", "데이터 무결성 위반이 발생했습니다.", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "예상치 못한 서버 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    SERVICE_UNAVAILABLE("SERVICE_UNAVAILABLE", "현재 서비스를 사용할 수 없습니다.", HttpStatus.SERVICE_UNAVAILABLE),
    BAD_REQUEST("BAD_REQUEST", "요청이 유효하지 않습니다.", HttpStatus.BAD_REQUEST),
    TOO_MANY_REQUESTS("TOO_MANY_REQUESTS", "너무 많은 요청이 발생했습니다.", HttpStatus.TOO_MANY_REQUESTS);

    private final String errorCode;
    private final String message;
    private final HttpStatus status;

    CoreErrorType(String errorCode, String message, HttpStatus status) {
        this.errorCode = errorCode;
        this.message = message;
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
