package com.continuous.backend.infrastructure;

import org.springframework.stereotype.Repository;

import com.continuous.backend.domain.Course;
import com.continuous.backend.domain.CourseRepository;
import com.continuous.backend.exception.CoreErrorType;
import com.continuous.backend.exception.CoreException;

@Repository
public class CourseCoreRepository implements CourseRepository {

    private final CourseJpaRepository CourseJpaRepository;

    public CourseCoreRepository(CourseJpaRepository CourseJpaRepository) {
        this.CourseJpaRepository = CourseJpaRepository;
    }

    @Override
    public Course findByProblemId(long problemId) {
        CourseEntity courseEntity = CourseJpaRepository.findByProblemId(problemId)
            .orElseThrow(() -> new CoreException(CoreErrorType.RESOURCE_NOT_FOUND));
        return courseEntity.toCourse();
    }
}
