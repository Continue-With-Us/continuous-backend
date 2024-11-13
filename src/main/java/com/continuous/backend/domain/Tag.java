package com.continuous.backend.domain;

import java.util.Arrays;

public enum Tag {
    JAVA, JAVASCRIPT;

    public static Tag from(String value) {
        return Arrays.stream(values())
            .filter(tag -> tag.name().equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 태그 값입니다: " + value));
    }
}
