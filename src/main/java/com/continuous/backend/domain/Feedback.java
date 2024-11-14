package com.continuous.backend.domain;

import lombok.Getter;

@Getter
public class Feedback {

    private final Long id;
    private final String content;

    public Feedback(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Feedback(String content) {
        this(null, content);
    }
}
