package com.continuous.backend.domain;

import java.util.List;

import lombok.Getter;

@Getter
public class ProblemWithMetadata {

    private final Problem problem;
    private final Course course;
    private final List<Tag> tags;

    public ProblemWithMetadata(Problem problem, Course course, List<Tag> tags) {
        this.problem = problem;
        this.course = course;
        this.tags = tags;
    }
}
