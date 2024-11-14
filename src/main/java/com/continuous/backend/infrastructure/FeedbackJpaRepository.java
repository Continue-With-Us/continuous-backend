package com.continuous.backend.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackJpaRepository extends JpaRepository<FeedbackEntity, Long> {
}
