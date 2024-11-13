package com.continuous.backend.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.continuous.backend.support.MockProblemRepository;

class ProblemServiceTest {

    private ProblemService problemService;

    @BeforeEach
    void setUp() {
        MockProblemRepository problemRepository = new MockProblemRepository();
        problemService = new ProblemService(problemRepository);
    }

    @DisplayName("문제 목록을 가져온다.")
    @Test
    void getProblems() {
        // when
        List<Problem> problems = problemService.getProblems();

        // then
        assertThat(problems).hasSize(2);
    }
}
