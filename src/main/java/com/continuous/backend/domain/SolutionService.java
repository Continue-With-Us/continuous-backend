package com.continuous.backend.domain;

import org.springframework.stereotype.Service;

@Service
public class SolutionService {

    private final SolutionRepository solutionRepository;

    public SolutionService(SolutionRepository solutionRepository) {
        this.solutionRepository = solutionRepository;
    }

    public Solution submit(String content, long problemId) {
        return solutionRepository.save(new Solution(content, problemId));
    }
}
