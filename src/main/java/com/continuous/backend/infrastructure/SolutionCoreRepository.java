package com.continuous.backend.infrastructure;

import org.springframework.stereotype.Repository;

import com.continuous.backend.domain.Solution;
import com.continuous.backend.domain.SolutionRepository;

@Repository
public class SolutionCoreRepository implements SolutionRepository {

    private final SolutionJpaRepository solutionJpaRepository;

    public SolutionCoreRepository(SolutionJpaRepository solutionJpaRepository) {
        this.solutionJpaRepository = solutionJpaRepository;
    }

    @Override
    public Solution save(Solution solution) {
        SolutionEntity solutionEntity = solutionJpaRepository.save(SolutionEntity.from(solution));
        return solutionEntity.toSolution();
    }
}
