package com.continuous.backend.domain;

import java.util.List;

public interface SolutionRepository {

    Solution save(Solution solution);

    List<Solution> findAllByProblemId(long problemId);
}
