package com.continuous.backend.domain;

import org.springframework.stereotype.Service;

import com.continuous.backend.exception.CoreErrorType;
import com.continuous.backend.exception.CoreException;

@Service
public class ProblemValidator {

    private final ProblemRepository problemRepository;

    public ProblemValidator(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public void validateProblemExists(long problemId) {
        boolean exists = problemRepository.existsById(problemId);
        if (!exists) {
            throw new CoreException(CoreErrorType.RESOURCE_NOT_FOUND);
        }
    }
}
