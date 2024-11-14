package com.continuous.backend.domain;

import org.springframework.stereotype.Service;

@Service
public class HintService {

    private final HintRepository hintRepository;
    private final ProblemValidator problemValidator;

    public HintService(HintRepository hintRepository, ProblemValidator problemValidator) {
        this.hintRepository = hintRepository;
        this.problemValidator = problemValidator;
    }

    public Hint getHint(long problemId) {
        problemValidator.validateProblemExists(problemId);
        return hintRepository.findByProblemId(problemId);
    }
}
