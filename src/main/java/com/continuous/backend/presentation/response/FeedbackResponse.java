package com.continuous.backend.presentation.response;

import com.continuous.backend.domain.Feedback;

public record FeedbackResponse(long id) {

    public static FeedbackResponse from(Feedback feedback) {
        return new FeedbackResponse(feedback.getId());
    }
}
