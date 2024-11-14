package com.continuous.backend.presentation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.continuous.backend.domain.Feedback;
import com.continuous.backend.domain.FeedbackService;
import com.continuous.backend.presentation.request.FeedbackRequest;
import com.continuous.backend.presentation.response.FeedbackResponse;
import com.continuous.backend.support.ApiResponse;

@RestController
@RequestMapping("/v1/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ApiResponse<FeedbackResponse> submit(@RequestBody FeedbackRequest request) {
        Feedback feedback = feedbackService.submit(request.content());

        return ApiResponse.success(FeedbackResponse.from(feedback));
    }
}
