package com.seoki.api.common.api.dto.response;

public record ApiResponse<T>(
        String code,
        String message,
        T data
) {
    public static ApiResponse<Void> success() {
        return new ApiResponse<>("200", "Success", null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("200", "Success", data);
    }

    public static ApiResponse<Void> error(String code, String message) {
        return new ApiResponse<>(code, message, null);
    }
}
