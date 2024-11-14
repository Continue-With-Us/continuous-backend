package com.continuous.backend.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.continuous.backend.exception.CoreErrorType;
import com.continuous.backend.exception.CoreException;
import com.continuous.backend.support.MockHintRepository;
import com.continuous.backend.support.MockProblemRepository;

class HintServiceTest {

    private HintService hintService;

    @BeforeEach
    void setUp() {
        HintRepository hintRepository = new MockHintRepository();
        ProblemRepository problemRepository = new MockProblemRepository();
        ProblemValidator problemValidator = new ProblemValidator(problemRepository);
        hintService = new HintService(hintRepository, problemValidator);
    }

    @DisplayName("getHint - 존재하는 문제 ID로 힌트를 조회한다.")
    @Test
    void getHint() {
        // given
        long problemId = 1L;

        // when
        Hint hint = hintService.getHint(problemId);

        // then
        assertThat(hint.getExampleCode()).isEqualTo("int sample = 1;");
        assertThat(hint.getResourceUrl()).isEqualTo("https://sample.sam");
    }

    @DisplayName("getHint - 존재하지 않는 문제 ID로 힌트를 조회하면 예외가 발생한다.")
    @Test
    void getHint_invalidProblemId() {
        long problemId = 9999L;

        // when & then
        assertThatThrownBy(() -> hintService.getHint(problemId))
            .isInstanceOf(CoreException.class)
            .hasMessageContaining(CoreErrorType.RESOURCE_NOT_FOUND.getMessage());
    }
}
