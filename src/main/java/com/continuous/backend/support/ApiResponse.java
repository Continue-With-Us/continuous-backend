package com.continuous.backend.support;

public record ApiResponse<T>(String status, String message, T data, String errorCode) {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("success", null, data, null);
    }

    public static <T> ApiResponse<T> error(String message, String errorCode) {
        return new ApiResponse<>("error", message, null, errorCode);
    }
}
