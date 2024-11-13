package com.continuous.backend.presentation.response;

import java.util.List;

import com.continuous.backend.domain.Problem;
import com.continuous.backend.domain.ProblemWithMetadata;

public record ProblemResponse(long id, String title, String course, List<String> tags) {
    public static ProblemResponse from(ProblemWithMetadata problemWithMetadata) {
        Problem problem = problemWithMetadata.getProblem();
        List<String> tags = problemWithMetadata.getTags().stream()
            .map(Enum::name)
            .toList();

        return new ProblemResponse(problem.getId(), problem.getTitle(), problemWithMetadata.getCourse().name(), tags);
    }
}
