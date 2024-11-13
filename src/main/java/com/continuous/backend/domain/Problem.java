package com.continuous.backend.domain;

public class Problem {

    private final long id;
    private final String title;

    public Problem(long id, String title) {
        validateTitle(title);

        this.id = id;
        this.title = title;
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank() || title.length() > 50) {
            throw new IllegalArgumentException("문제의 제목은 비어있거나 50자를 넘을 수 없습니다.");
        }
    }
}
