package com.continuous.backend.support;

import com.continuous.backend.domain.Hint;
import com.continuous.backend.domain.HintRepository;
import com.continuous.backend.exception.CoreErrorType;
import com.continuous.backend.exception.CoreException;

public class MockHintRepository implements HintRepository {

    @Override
    public Hint findByProblemId(long problemId) {
        if (problemId == 1) {
            return new Hint(1L, "int sample = 1;", "https://sample.sam", 1L);
        }
        throw new CoreException(CoreErrorType.RESOURCE_NOT_FOUND);
    }
}
