package com.continuous.backend.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseJpaRepository extends JpaRepository<CourseEntity, Long> {

    @Query(value = "SELECT c.id AS id, c.name AS name FROM course c JOIN problem_course pc ON c.id = pc.course_id WHERE pc.problem_id = :problemId", nativeQuery = true)
    Optional<CourseEntity> findByProblemId(@Param("problemId") long problemId);
}
