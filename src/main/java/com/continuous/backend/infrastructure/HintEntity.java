package com.continuous.backend.infrastructure;

import com.continuous.backend.domain.Hint;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hint")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HintEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "example_code", length = 1000)
    private String exampleCode;

    @Column(name = "resource_url")
    private String resourceUrl;

    @Column(name = "problem_id")
    private long problemId;

    public Hint toHint() {
        return new Hint(id, exampleCode, resourceUrl, problemId);
    }
}
