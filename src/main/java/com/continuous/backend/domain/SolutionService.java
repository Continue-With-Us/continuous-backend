package com.continuous.backend.domain;

import org.springframework.stereotype.Service;

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
            throw new IllegalArgumentException("ID에 해당하는 문제가 없습니다. problemId: " + problemId);
        }
        return solutionRepository.save(new Solution(content, problemId));
    }
}
