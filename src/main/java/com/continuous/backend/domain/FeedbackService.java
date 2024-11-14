package com.continuous.backend.domain;

import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Feedback submit(String content) {
        Feedback feedback = new Feedback(content);
        return feedbackRepository.save(feedback);
    }
}
