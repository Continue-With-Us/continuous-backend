package com.continuous.backend.domain;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public List<Problem> getProblems() {
        return problemRepository.findAll();
    }
}
