package com.continuous.backend.domain;

import lombok.Getter;

@Getter
public class Hint {

    private final long id;
    private final String exampleCode;
    private final String resourceUrl;
    private final long problemId;

    public Hint(long id, String exampleCode, String resourceUrl, long problemId) {
        this.id = id;
        this.exampleCode = exampleCode;
        this.resourceUrl = resourceUrl;
        this.problemId = problemId;
    }
}
