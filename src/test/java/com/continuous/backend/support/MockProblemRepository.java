package com.continuous.backend.support;

import java.util.ArrayList;
import java.util.List;

import com.continuous.backend.domain.Problem;
import com.continuous.backend.domain.ProblemRepository;

public class MockProblemRepository implements ProblemRepository {

    @Override
    public List<Problem> findAll() {
        // 샘플 데이터 생성
        Problem problem1 = new Problem(1L, "자바 Enum에 대해서 설명해주세요.");
        Problem problem2 = new Problem(2L, "클로저에 대해서 설명해주세요.");

        // 반환할 리스트
        List<Problem> problems = new ArrayList<>();
        problems.add(problem1);
        problems.add(problem2);

        return problems;
    }
}
