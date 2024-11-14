package com.continuous.backend.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SolutionTest {

    @DisplayName("답변의 길이는 100자 이하여야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void create_blank(String content) {
        // when & then
        assertThatThrownBy(() -> new Solution(content, 1L))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("답변은 비어있거나 100자를 넘을 수 없습니다.");
    }

    @DisplayName("답변의 길이는 100자 이하여야 한다.")
    @Test
    void create_overSize() {
        // given
        String content = "백글자이상답변입니다백글자이상답변입니다백글자이상답변입니다백글자이상답변입니다백글자이상답변입니다백글자이상답변입니다백글자이상답변입니다백글자이상답변입니다백글자이상답변입니다백글자이상답변입니다백";

        // when & then
        assertThatThrownBy(() -> new Solution(content, 1L))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("답변은 비어있거나 100자를 넘을 수 없습니다.");
    }
}
