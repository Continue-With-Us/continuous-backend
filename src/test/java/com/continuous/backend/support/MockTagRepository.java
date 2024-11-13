package com.continuous.backend.support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.continuous.backend.domain.Tag;
import com.continuous.backend.domain.TagRepository;

public class MockTagRepository implements TagRepository {

    @Override
    public List<Tag> findAllByProblemId(long problemId) {
        if (problemId == 1) {
            return Arrays.asList(Tag.JAVA);
        } else if (problemId == 2) {
            return Arrays.asList(Tag.JAVASCRIPT);
        } else {
            return new ArrayList<>(); // 빈 리스트 반환
        }
    }
}
