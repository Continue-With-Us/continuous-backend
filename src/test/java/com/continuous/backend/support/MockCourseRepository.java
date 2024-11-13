package com.continuous.backend.support;

import com.continuous.backend.domain.Course;
import com.continuous.backend.domain.CourseRepository;

public class MockCourseRepository implements CourseRepository {

    @Override
    public Course findByProblemId(long problemId) {
        if (problemId == 1) {
            return Course.BACKEND;
        } else if (problemId == 2) {
            return Course.FRONTEND;
        } else {
            return null;
        }
    }
}
