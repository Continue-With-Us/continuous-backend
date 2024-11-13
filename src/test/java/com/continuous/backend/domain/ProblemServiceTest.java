package com.continuous.backend.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.continuous.backend.support.MockCourseRepository;
import com.continuous.backend.support.MockProblemRepository;
import com.continuous.backend.support.MockTagRepository;

class ProblemServiceTest {

    private ProblemService problemService;

    @BeforeEach
    void setUp() {
        MockProblemRepository problemRepository = new MockProblemRepository();
        MockCourseRepository courseRepository = new MockCourseRepository();
        MockTagRepository tagRepository = new MockTagRepository();
        problemService = new ProblemService(problemRepository, courseRepository, tagRepository);
    }

    @DisplayName("문제 목록을 가져온다.")
    @Test
    void getProblems() {
        // when
        List<Problem> problems = problemService.getProblems();

        // then
        assertThat(problems).hasSize(2);
    }

    @DisplayName("문제 메타데이터 목록을 가져온다.")
    @Test
    void getProblemWithMetadata() {
        // when
        List<ProblemWithMetadata> problems = problemService.getProblemsWithMetadata();

        //then
        assertThat(problems).hasSize(2);
        assertThat(problems.get(0).getCourse()).isEqualTo(Course.BACKEND);
        assertThat(problems.get(0).getTags()).containsExactly(Tag.JAVA);
        assertThat(problems.get(1).getCourse()).isEqualTo(Course.FRONTEND);
        assertThat(problems.get(1).getTags()).containsExactly(Tag.JAVASCRIPT);
    }
}
