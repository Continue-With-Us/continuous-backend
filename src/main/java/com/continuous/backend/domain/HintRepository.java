package com.continuous.backend.domain;

public interface HintRepository {

    Hint findByProblemId(long problemId);
}
