package com.continuous.backend.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemJpaRepository extends JpaRepository<ProblemEntity, Long> {
}
