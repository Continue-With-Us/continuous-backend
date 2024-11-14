package com.continuous.backend.presentation.response;

import com.continuous.backend.domain.Solution;

public record SolutionResponse(long solutionId, long problemId, String content) {

    public static SolutionResponse from(Solution solution) {
        return new SolutionResponse(solution.getId(), solution.getProblemId(), solution.getContent());
    }
}
