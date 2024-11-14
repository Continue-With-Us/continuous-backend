package com.continuous.backend.infrastructure;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import com.continuous.backend.domain.Hint;
import com.continuous.backend.exception.CoreErrorType;
import com.continuous.backend.exception.CoreException;

@DataJpaTest
@Import(HintCoreRepository.class)
class HintCoreRepositoryTest {

    @Autowired
    private HintCoreRepository hintCoreRepository;

    @DisplayName("문제 ID에 해당하는 힌트를 조회한다.")
    @Test
    // TODO: sql 사용방식 통일
    @Sql("/hint-test-data.sql")
    void findByProblemId() {
        // given
        long problemId = 1L;

        // when
        Hint hint = hintCoreRepository.findByProblemId(problemId);

        //then
        assertThat(hint.getExampleCode()).isEqualTo("int test = 1;");
        assertThat(hint.getResourceUrl()).isEqualTo("https://example.com");
    }

    @DisplayName("문제 ID에 해당하는 힌트를 조회한다.")
    @Test
    @Sql("/hint-test-data.sql")
    void findByProblemId_invalidProblemId() {
        // given
        long problemId = 9999L;

        // when & then
        assertThatThrownBy(() -> hintCoreRepository.findByProblemId(problemId))
            .isInstanceOf(CoreException.class)
            .hasMessage(CoreErrorType.RESOURCE_NOT_FOUND.getMessage());
    }
}
