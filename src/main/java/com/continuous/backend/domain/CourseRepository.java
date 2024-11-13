package com.continuous.backend.domain;

public interface CourseRepository {

    Course findByProblemId(long problemId);
}
