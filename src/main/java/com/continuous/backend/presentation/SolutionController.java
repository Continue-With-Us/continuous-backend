package com.continuous.backend.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.continuous.backend.domain.Solution;
import com.continuous.backend.domain.SolutionService;
import com.continuous.backend.presentation.request.SubmitSolutionRequest;
import com.continuous.backend.presentation.response.SolutionResponse;
import com.continuous.backend.support.ApiResponse;

@RestController
@RequestMapping("/v1/problems")
public class SolutionController {

    private final SolutionService solutionService;

    public SolutionController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @PostMapping("/{id}/solutions")
    public ApiResponse<SolutionResponse> submit(@PathVariable("id") long problemId, @RequestBody SubmitSolutionRequest request) {
        Solution solution = solutionService.submit(request.content(), problemId);

        return ApiResponse.success(SolutionResponse.from(solution));
    }

    @GetMapping("/{id}/solutions")
    public ApiResponse<List<SolutionResponse>> getSolutions(@PathVariable("id") long problemId) {
        List<Solution> solutions = solutionService.getSolutions(problemId);
        List<SolutionResponse> responses = solutions.stream().map(SolutionResponse::from).toList();

        return ApiResponse.success(responses);
    }
}
