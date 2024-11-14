package com.continuous.backend.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.continuous.backend.exception.CoreException;

public class CourseTest {

    @DisplayName("유효한 코스 값을 변환할 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"BACKEND", "FRONTEND"})
    void from_validValue(String value) {
        // when
        Course course = Course.from(value);

        // then
        assertThat(course).isNotNull();
        assertThat(course.name()).isEqualTo(value);
    }

    @DisplayName("유효하지 않은 코스 값은 예외를 발생시킨다.")
    @Test
    void from_invalidValue_throwsException() {
        // when & then
        assertThatThrownBy(() -> Course.from("INVALID"))
            .isInstanceOf(CoreException.class);
    }
}
