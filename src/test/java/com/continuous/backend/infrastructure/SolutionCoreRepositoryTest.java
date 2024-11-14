package com.continuous.backend.infrastructure;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

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
}
