package com.continuous.backend.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.continuous.backend.exception.CoreErrorType;
import com.continuous.backend.exception.CoreException;

class ProblemTest {

    @DisplayName("문제의 제목은 비어있을 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void create_emptyTitle(String title) {
        // when & then
        assertThatThrownBy(() -> new Problem(1L, title))
            .isInstanceOf(CoreException.class)
            .hasMessage(CoreErrorType.PROBLEM_TITLE_EMPTY.getMessage());
    }

    @DisplayName("문제의 제목은 50글자를 넘을 수 없다.")
    @Test
    void create_overFiftySizeTitle() {
        // given
        String title = "오십한글자문제의제목오십한글자문제의제목오십한글자문제의제목오십한글자문제의제목오십한글자문제의제목오";

        // when & then
        assertThatThrownBy(() -> new Problem(1L, title))
            .isInstanceOf(CoreException.class)
            .hasMessage(CoreErrorType.PROBLEM_TITLE_TOO_LONG.getMessage());
    }
}
