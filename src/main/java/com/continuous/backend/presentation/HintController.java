package com.continuous.backend.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.continuous.backend.domain.Hint;
import com.continuous.backend.domain.HintService;
import com.continuous.backend.presentation.response.HintResponse;
import com.continuous.backend.support.ApiResponse;

@RestController
@RequestMapping("/v1/problems")
public class HintController {

    private final HintService hintService;

    public HintController(HintService hintService) {
        this.hintService = hintService;
    }

    @GetMapping("/{id}/hints")
    public ApiResponse<HintResponse> getHint(@PathVariable("id") long problemId) {
        Hint hint = hintService.getHint(problemId);
        HintResponse response = HintResponse.from(hint);

        return ApiResponse.success(response);
    }
}
