package com.continuous.backend.domain;

import java.util.Objects;

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
        if (content == null || content.isBlank() || content.length() > 100) {
            throw new IllegalArgumentException("답변은 비어있거나 100자를 넘을 수 없습니다.");
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