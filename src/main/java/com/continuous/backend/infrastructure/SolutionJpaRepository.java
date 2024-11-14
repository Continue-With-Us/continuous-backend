package com.continuous.backend.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionJpaRepository extends JpaRepository<SolutionEntity, Long> {

    List<SolutionEntity> findAllByProblemId(long problemId);
}
