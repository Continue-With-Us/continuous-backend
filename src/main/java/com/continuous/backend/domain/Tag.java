package com.continuous.backend.domain;

import java.util.Arrays;

import com.continuous.backend.exception.CoreErrorType;
import com.continuous.backend.exception.CoreException;

public enum Tag {
    JAVA, JAVASCRIPT;

    public static Tag from(String value) {
        return Arrays.stream(values())
            .filter(tag -> tag.name().equals(value))
            .findFirst()
            .orElseThrow(() -> new CoreException(CoreErrorType.RESOURCE_NOT_FOUND));
    }
}
