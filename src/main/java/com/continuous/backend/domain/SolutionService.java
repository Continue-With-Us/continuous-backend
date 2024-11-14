package com.continuous.backend.domain;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SolutionService {

    private final SolutionRepository solutionRepository;
    private final ProblemValidator problemValidator;

    public SolutionService(SolutionRepository solutionRepository, ProblemValidator problemValidator) {
        this.solutionRepository = solutionRepository;
        this.problemValidator = problemValidator;
    }

    public Solution submit(String content, long problemId) {
        problemValidator.validateProblemExists(problemId);
        return solutionRepository.save(new Solution(content, problemId));
    }

    public List<Solution> getSolutions(long problemId) {
        problemValidator.validateProblemExists(problemId);
        return solutionRepository.findAllByProblemId(problemId);
    }
}
