package com.continuous.backend.domain;

import java.util.Objects;

import com.continuous.backend.exception.CoreErrorType;
import com.continuous.backend.exception.CoreException;

import lombok.Getter;

@Getter
public class Solution {

    private final Long id;
    private final String content;
    private final long problemId;

    public Solution(Long id, String content, long problemId) {
        validateContent(content);

        this.id = id;
        this.content = content;
        this.problemId = problemId;
    }

    public Solution(String content, long problemId) {
        this(null, content, problemId);
    }

    private void validateContent(String content) {
        if (content == null || content.isBlank()) {
            throw new CoreException(CoreErrorType.SOLUTION_CONTENT_EMPTY);
        }
        if (content.length() > 100) {
            throw new CoreException(CoreErrorType.SOLUTION_CONTENT_TOO_LONG);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Solution solution = (Solution)o;
        return id == solution.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
