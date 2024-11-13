package com.continuous.backend.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TagTest {

    @DisplayName("유효한 태그 값을 변환할 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"JAVA", "JAVASCRIPT"})
    void from(String value) {
        // when
        Tag tag = Tag.from(value);

        // then
        assertThat(tag).isNotNull();
        assertThat(tag.name()).isEqualToIgnoringCase(value);
    }

    @DisplayName("유효하지 않은 태그 값은 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"PYTHON", "C++", "UNKNOWN"})
    void from_invalidValue_throwsException(String value) {
        // when & then
        assertThatThrownBy(() -> Tag.from(value))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
