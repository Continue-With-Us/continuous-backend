package com.continuous.backend.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TagJpaRepository extends JpaRepository<TagEntity, Long> {

    @Query(value = "SELECT t.id, t.name FROM tag as t JOIN problem_tag as pt on t.id = pt.tag_id WHERE pt.problem_id = :problemId", nativeQuery = true)
    List<TagEntity> findAllByProblemId(@Param("problemId") long problemId);
}
