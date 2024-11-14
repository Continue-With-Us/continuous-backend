package com.continuous.backend.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.continuous.backend.exception.CoreErrorType;
import com.continuous.backend.exception.CoreException;
import com.continuous.backend.support.MockProblemRepository;
import com.continuous.backend.support.MockSolutionRepository;

class SolutionServiceTest {

    private SolutionService solutionService;

    @BeforeEach
    void setUp() {
        SolutionRepository solutionRepository = new MockSolutionRepository();
        ProblemRepository problemRepository = new MockProblemRepository();
        ProblemValidator problemValidator = new ProblemValidator(problemRepository);
        solutionService = new SolutionService(solutionRepository, problemValidator);
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
            .isInstanceOf(CoreException.class)
            .hasMessage(CoreErrorType.RESOURCE_NOT_FOUND.getMessage());
    }

    @DisplayName("문제의 모든 솔루션을 조회한다.")
    @Test
    void getSolutions() {
        // given
        long problemId = 1L;
        String content1 = "나의 답변 1";
        solutionService.submit(content1, problemId);
        String content2 = "나의 답변 2";
        solutionService.submit(content2, problemId);

        // when
        List<Solution> solutions = solutionService.getSolutions(problemId);

        //then
        assertThat(solutions).hasSize(2);
        assertThat(solutions.get(0).getContent()).isEqualTo(content1);
        assertThat(solutions.get(1).getContent()).isEqualTo(content2);
    }
}
