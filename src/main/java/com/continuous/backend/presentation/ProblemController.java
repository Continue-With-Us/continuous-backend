package com.continuous.backend.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.continuous.backend.domain.ProblemService;
import com.continuous.backend.domain.ProblemWithMetadata;
import com.continuous.backend.presentation.response.ProblemResponse;
import com.continuous.backend.support.ApiResponse;

@RestController
@RequestMapping("/v1/problems")
public class ProblemController {

    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GetMapping
    public ApiResponse<List<ProblemResponse>> getProblems() {
        List<ProblemWithMetadata> problemsWithMetadata = problemService.getProblemsWithMetadata();
        List<ProblemResponse> responses = problemsWithMetadata.stream()
            .map(ProblemResponse::from)
            .toList();

        return ApiResponse.success(responses);
    }
}
