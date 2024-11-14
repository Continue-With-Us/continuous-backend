package com.continuous.backend.infrastructure;

import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public Problem findById(long problemId) {
        ProblemEntity problemEntity = problemJpaRepository.findById(problemId)
            .orElseThrow(() -> new NoSuchElementException("ID에 해당하는 문제가 없습니다. ID: " + problemId));

        return problemEntity.toProblem();
    }

    @Override
    public boolean existsById(long problemId) {
        return problemJpaRepository.existsById(problemId);
    }
}
