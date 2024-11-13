package com.continuous.backend.domain;

import java.util.List;

public class Problem {

    private final long id;
    private final String title;
    private final List<Tag> tags;
    private final Course course;

    public Problem(long id, String title, List<Tag> tags, Course course) {
        validateTitle(title);

        this.id = id;
        this.title = title;
        this.tags = tags;
        this.course = course;
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank() || title.length() > 50) {
            throw new IllegalArgumentException("문제의 제목은 비어있거나 50자를 넘을 수 없습니다.");
        }
    }
}
