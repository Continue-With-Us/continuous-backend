package com.continuous.backend.infrastructure;

import org.springframework.stereotype.Repository;

import com.continuous.backend.domain.Hint;
import com.continuous.backend.domain.HintRepository;
import com.continuous.backend.exception.CoreErrorType;
import com.continuous.backend.exception.CoreException;

@Repository
public class HintCoreRepository implements HintRepository {

    private final HintJpaRepository hintJpaRepository;

    public HintCoreRepository(HintJpaRepository hintJpaRepository) {
        this.hintJpaRepository = hintJpaRepository;
    }

    @Override
    public Hint findByProblemId(long problemId) {
        HintEntity hintEntity = hintJpaRepository.findByProblemId(problemId).orElseThrow(() -> new CoreException(CoreErrorType.RESOURCE_NOT_FOUND));
        return hintEntity.toHint();
    }
}
