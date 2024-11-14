package com.continuous.backend.infrastructure;

import org.springframework.stereotype.Repository;

import com.continuous.backend.domain.Feedback;
import com.continuous.backend.domain.FeedbackRepository;

@Repository
public class FeedbackCoreRepository implements FeedbackRepository {

    private final FeedbackJpaRepository feedbackJpaRepository;

    public FeedbackCoreRepository(FeedbackJpaRepository feedbackJpaRepository) {
        this.feedbackJpaRepository = feedbackJpaRepository;
    }

    @Override
    public Feedback save(Feedback feedback) {
        FeedbackEntity feedbackEntity = feedbackJpaRepository.save(FeedbackEntity.from(feedback));
        return feedbackEntity.toFeedback();
    }
}
