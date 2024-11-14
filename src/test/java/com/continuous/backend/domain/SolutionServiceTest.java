package com.continuous.backend.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.continuous.backend.support.MockProblemRepository;
import com.continuous.backend.support.MockSolutionRepository;

class SolutionServiceTest {

    private SolutionService solutionService;

    @BeforeEach
    void setUp() {
        SolutionRepository solutionRepository = new MockSolutionRepository();
        ProblemRepository problemRepository = new MockProblemRepository();
        solutionService = new SolutionService(solutionRepository, problemRepository);
    }

    @DisplayName("솔루션을 제출한다.")
    @Test
    void submit() {
        // given
        String content = "나의 답변";
        long problemId = 1L;

        // when
        Solution solution = solutionService.submit(content, problemId);

        //then
        assertThat(solution.getId()).isEqualTo(1L);
        assertThat(solution.getContent()).isEqualTo(content);
    }

    @DisplayName("존재하지 않는 문제 ID로 요청하면 예외를 발생한다.")
    @Test
    void submit_invalidProblemId() {
        // given
        String content = "나의 답변";
        long problemId = 9999L;

        // when & then
        assertThatThrownBy(() -> solutionService.submit(content, problemId))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
