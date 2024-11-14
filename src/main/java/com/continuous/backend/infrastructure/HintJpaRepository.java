package com.continuous.backend.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HintJpaRepository extends JpaRepository<HintEntity, Long> {

    Optional<HintEntity> findByProblemId(long problemId);
}
