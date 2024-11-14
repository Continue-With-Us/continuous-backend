package com.continuous.backend.domain;

import org.springframework.stereotype.Service;

import com.continuous.backend.exception.CoreErrorType;
import com.continuous.backend.exception.CoreException;

@Service
public class SolutionService {

    private final SolutionRepository solutionRepository;
    private final ProblemRepository problemRepository;

    public SolutionService(SolutionRepository solutionRepository, ProblemRepository problemRepository) {
        this.solutionRepository = solutionRepository;
        this.problemRepository = problemRepository;
    }

    public Solution submit(String content, long problemId) {
        boolean exists = problemRepository.existsById(problemId);
        if (!exists) {
            throw new CoreException(CoreErrorType.RESOURCE_NOT_FOUND);
        }
        return solutionRepository.save(new Solution(content, problemId));
    }
}
