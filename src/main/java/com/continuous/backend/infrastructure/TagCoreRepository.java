package com.continuous.backend.infrastructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.continuous.backend.domain.Tag;
import com.continuous.backend.domain.TagRepository;

@Repository
public class TagCoreRepository implements TagRepository {

    private final TagJpaRepository tagJpaRepository;

    public TagCoreRepository(TagJpaRepository tagJpaRepository) {
        this.tagJpaRepository = tagJpaRepository;
    }

    @Override
    public List<Tag> findAllByProblemId(long problemId) {
        return tagJpaRepository.findAllByProblemId(problemId).stream()
            .map(TagEntity::toTag)
            .toList();
    }
}
