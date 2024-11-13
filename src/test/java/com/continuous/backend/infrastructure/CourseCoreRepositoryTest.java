package com.continuous.backend.infrastructure;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import com.continuous.backend.domain.Course;
import com.continuous.backend.domain.CourseRepository;

@DataJpaTest
@Import(CourseCoreRepository.class)
class CourseCoreRepositoryTest {

    @Autowired
    private CourseRepository courseCoreRepository;

    @DisplayName("문제 ID에 해당하는 코스를 조회한다.")
    @Test
    @Sql(scripts = "/course-test-data.sql")
    void findByProblemId() {
        // given
        long problemId = 1L;

        // when
        Course course = courseCoreRepository.findByProblemId(problemId);

        //then
        assertThat(course).isNotNull();
        assertThat(course).isEqualTo(Course.BACKEND);
    }
}
