package com.continuous.backend.domain;

import java.util.List;

public interface ProblemRepository {

    List<Problem> findAll();
}
