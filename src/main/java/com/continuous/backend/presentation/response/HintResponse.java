package com.continuous.backend.presentation.response;

import com.continuous.backend.domain.Hint;

public record HintResponse(long problemId, long hintId, String exampleCode, String resourceUrl) {

    public static HintResponse from(Hint hint) {
        return new HintResponse(hint.getProblemId(), hint.getId(), hint.getExampleCode(), hint.getResourceUrl());
    }
}
