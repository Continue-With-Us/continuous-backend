package com.continuous.backend.domain;

import lombok.Getter;

@Getter
public class Hint {

    private final long id;
    private final String exampleCode;
    private final String resourceUrl;

    public Hint(long id, String exampleCode, String resourceUrl) {
        this.id = id;
        this.exampleCode = exampleCode;
        this.resourceUrl = resourceUrl;
    }
}
