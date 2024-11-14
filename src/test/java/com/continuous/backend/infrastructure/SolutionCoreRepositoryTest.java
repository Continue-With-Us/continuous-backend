package com.continuous.backend.infrastructure;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import com.continuous.backend.domain.Solution;

@DataJpaTest
@Import(SolutionCoreRepository.class)
class SolutionCoreRepositoryTest {

    @Autowired
    private SolutionCoreRepository solutionCoreRepository;

    @DisplayName("답변을 저장한다.")
    @Test
    void save() {
        // given
        Solution solution = new Solution("나의 답변", 1L);

        // when
        Solution savedSolution = solutionCoreRepository.save(solution);

        // then
        assertThat(savedSolution.getContent()).isEqualTo("나의 답변");
    }

    @DisplayName("문제의 전체 답변을 조회한다.")
    @Test
    @Sql("/solution-test-data.sql")
    void findAll() {
        // given
        long problemId = 1L;

        // when
        List<Solution> solutions = solutionCoreRepository.findAllByProblemId(problemId);

        //then
        assertThat(solutions).hasSize(2);
    }
}
