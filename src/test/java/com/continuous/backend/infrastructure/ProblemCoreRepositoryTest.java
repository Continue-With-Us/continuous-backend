package com.continuous.backend.infrastructure;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import com.continuous.backend.domain.Problem;
import com.continuous.backend.exception.CoreErrorType;
import com.continuous.backend.exception.CoreException;

@DataJpaTest
@Import(ProblemCoreRepository.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProblemCoreRepositoryTest {

    @Autowired
    private ProblemCoreRepository problemCoreRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeAll
    void setUp() throws IOException {
        executeSqlScript("problem-test-data.sql");
    }

    private void executeSqlScript(String scriptPath) throws IOException {
        String sql = new String(getClass().getClassLoader().getResourceAsStream(scriptPath).readAllBytes());
        jdbcTemplate.execute(sql);
    }

    @DisplayName("전체 문제를 조회한다.")
    @Test
    void findAll() {
        // when
        List<Problem> problems = problemCoreRepository.findAll();

        // then
        assertThat(problems).hasSize(3);
    }

    @DisplayName("특정 ID에 해당하는 문제를 조회한다.")
    @Test
    void findById() {
        // given
        long problemId = 1L;

        // when
        Problem problem = problemCoreRepository.findById(problemId);

        // then
        assertThat(problem.getId()).isEqualTo(problemId);
        assertThat(problem.getTitle()).isEqualTo("클로저에 대해서 설명해주세요");
    }

    @DisplayName("특정 ID에 해당하는 문제가 없으면 예외를 발생한다.")
    @Test
    void findById_invalidId() {
        // given
        long problemId = 4L;

        // when & then
        assertThatThrownBy(() -> problemCoreRepository.findById(problemId))
            .isInstanceOf(CoreException.class)
            .hasMessage(CoreErrorType.RESOURCE_NOT_FOUND.getMessage());
    }
}

