package com.continuous.backend.domain;

import java.util.Arrays;

import com.continuous.backend.exception.CoreErrorType;
import com.continuous.backend.exception.CoreException;

public enum Course {
    BACKEND, FRONTEND;

    public static Course from(String value) {
        return Arrays.stream(values())
            .filter(course -> course.name().equals(value))
            .findFirst()
            .orElseThrow(() -> new CoreException(CoreErrorType.RESOURCE_NOT_FOUND));
    }
}
