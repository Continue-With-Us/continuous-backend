package com.continuous.backend.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.continuous.backend.support.MockSolutionRepository;

class SolutionServiceTest {

    private SolutionService solutionService;

    @BeforeEach
    void setUp() {
        MockSolutionRepository solutionRepository = new MockSolutionRepository();
        solutionService = new SolutionService(solutionRepository);
    }

    @DisplayName("솔루션을 제출한다.")
    @Test
    void submit() {
        // given
        String content = "나의 답변";

        // when
        Solution solution = solutionService.submit(content);

        //then
        assertThat(solution.getId()).isEqualTo(1L);
        assertThat(solution.getContent()).isEqualTo(content);
    }
}
