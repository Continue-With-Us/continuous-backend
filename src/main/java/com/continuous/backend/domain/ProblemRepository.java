package com.continuous.backend.domain;

import java.util.List;

public interface ProblemRepository {

    List<Problem> findAll();

    Problem findById(long problemId);

    boolean existsById(long problemId);
}
