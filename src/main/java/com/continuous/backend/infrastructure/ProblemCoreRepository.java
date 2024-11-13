package com.continuous.backend.infrastructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.continuous.backend.domain.Problem;
import com.continuous.backend.domain.ProblemRepository;

@Repository
public class ProblemCoreRepository implements ProblemRepository {

    private final ProblemJpaRepository problemJpaRepository;

    public ProblemCoreRepository(ProblemJpaRepository problemJpaRepository) {
        this.problemJpaRepository = problemJpaRepository;
    }

    @Override
    public List<Problem> findAll() {
        return problemJpaRepository.findAll().stream()
            .map(ProblemEntity::toProblem)
            .toList();
    }
}