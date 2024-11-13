package com.continuous.backend.infrastructure;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import com.continuous.backend.domain.Tag;
import com.continuous.backend.domain.TagRepository;

@DataJpaTest
@Import(TagCoreRepository.class)
public class TagCoreRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @DisplayName("Problem ID로 태그를 조회한다")
    @Sql(scripts = "/tag-test-data.sql")
    @Test
    void findAllByProblemId() {
        // Given
        long problemId = 1L;

        // When
        List<Tag> tags = tagRepository.findAllByProblemId(problemId);

        // Then
        assertThat(tags).isNotEmpty();
        assertThat(tags).hasSize(2);
        assertThat(tags).containsExactly(Tag.JAVA, Tag.JAVASCRIPT);
    }
}
