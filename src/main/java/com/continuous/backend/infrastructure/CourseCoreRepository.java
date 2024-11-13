package com.continuous.backend.infrastructure;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Repository;

import com.continuous.backend.domain.Course;
import com.continuous.backend.domain.CourseRepository;

@Repository
public class CourseCoreRepository implements CourseRepository {

    private final CourseJpaRepository CourseJpaRepository;

    public CourseCoreRepository(CourseJpaRepository CourseJpaRepository) {
        this.CourseJpaRepository = CourseJpaRepository;
    }

    @Override
    public Course findByProblemId(long problemId) {
        CourseEntity courseEntity = CourseJpaRepository.findByProblemId(problemId)
            .orElseThrow(() -> new NoSuchElementException("해당 problemId 에 해당하는 코스가 존재하지 않습니다. problemId: " + problemId));
        return courseEntity.toCourse();
    }
}
