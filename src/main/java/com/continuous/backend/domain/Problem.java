package com.continuous.backend.domain;

import java.util.Objects;

import com.continuous.backend.exception.CoreErrorType;
import com.continuous.backend.exception.CoreException;

import lombok.Getter;

@Getter
public class Problem {

    private final long id;
    private final String title;

    public Problem(long id, String title) {
        validateTitle(title);

        this.id = id;
        this.title = title;
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new CoreException(CoreErrorType.PROBLEM_TITLE_EMPTY);
        }
        if (title.length() > 50) {
            throw new CoreException(CoreErrorType.PROBLEM_TITLE_TOO_LONG);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Problem problem = (Problem)o;
        return id == problem.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
