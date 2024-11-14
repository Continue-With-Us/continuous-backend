package com.continuous.backend.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.continuous.backend.exception.CoreErrorType;
import com.continuous.backend.exception.CoreException;

class SolutionTest {

    @DisplayName("답변의 길이는 100자 이하여야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void create_blank(String content) {
        // when & then
        assertThatThrownBy(() -> new Solution(content, 1L))
            .isInstanceOf(CoreException.class)
            .hasMessage(CoreErrorType.SOLUTION_CONTENT_EMPTY.getMessage());
    }

    @DisplayName("답변의 길이는 100자 이하여야 한다.")
    @Test
    void create_overSize() {
        // given
        String content = "백글자이상답변입니다백글자이상답변입니다백글자이상답변입니다백글자이상답변입니다백글자이상답변입니다백글자이상답변입니다백글자이상답변입니다백글자이상답변입니다백글자이상답변입니다백글자이상답변입니다백";

        // when & then
        assertThatThrownBy(() -> new Solution(content, 1L))
            .isInstanceOf(CoreException.class)
            .hasMessage(CoreErrorType.SOLUTION_CONTENT_TOO_LONG.getMessage());
    }
}
