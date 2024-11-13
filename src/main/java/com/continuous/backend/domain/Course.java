package com.continuous.backend.domain;

import java.util.Arrays;

public enum Course {
    BACKEND, FRONTEND;

    public static Course from(String value) {
        return Arrays.stream(values())
            .filter(course -> course.name().equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않는 코스 값 입니다: " + value));
    }
}
