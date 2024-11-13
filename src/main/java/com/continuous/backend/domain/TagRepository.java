package com.continuous.backend.domain;

import java.util.List;

public interface TagRepository {

    List<Tag> findAllByProblemId(long problemId);
}
