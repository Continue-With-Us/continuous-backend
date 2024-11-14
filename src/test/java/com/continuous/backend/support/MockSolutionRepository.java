package com.continuous.backend.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.continuous.backend.domain.Solution;
import com.continuous.backend.domain.SolutionRepository;

public class MockSolutionRepository implements SolutionRepository {

    private final Map<Long, Solution> storage = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public Solution save(Solution solution) {
        if (solution.getId() == null) {
            Solution newSolution = new Solution(idGenerator.incrementAndGet(), solution.getContent(), solution.getProblemId());
            storage.put(newSolution.getId(), solution);
            return newSolution;
        }
        storage.replace(solution.getId(), solution);
        return solution;
    }

    @Override
    public List<Solution> findAllByProblemId(long problemId) {
        ArrayList<Solution> solutions = new ArrayList<>();
        for (Solution solution : storage.values()) {
            if (solution.getProblemId() == problemId) {
                solutions.add(solution);
            }
        }
        return solutions;
    }
}
