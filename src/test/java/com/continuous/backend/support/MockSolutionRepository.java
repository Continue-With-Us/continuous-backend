package com.continuous.backend.support;

import java.util.HashMap;
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
            Solution newSolution = new Solution(idGenerator.incrementAndGet(), solution.getContent());
            storage.put(newSolution.getId(), solution);
            return newSolution;
        }
        storage.replace(solution.getId(), solution);
        return solution;
    }

    public Solution findById(Long id) {
        return storage.get(id);
    }

    public Map<Long, Solution> findAll() {
        return new HashMap<>(storage);
    }
}
