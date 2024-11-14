package com.continuous.backend.infrastructure;

import com.continuous.backend.domain.Solution;

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
@Table(name = "solution")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SolutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "problem_id")
    private long problemId;

    public SolutionEntity(String content, long problemId) {
        this.content = content;
        this.problemId = problemId;
    }

    public static SolutionEntity from(Solution solution) {
        return new SolutionEntity(solution.getContent(), solution.getProblemId());
    }

    public Solution toSolution() {
        return new Solution(id, content, problemId);
    }
}
