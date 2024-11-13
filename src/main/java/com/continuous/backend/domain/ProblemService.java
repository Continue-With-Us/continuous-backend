package com.continuous.backend.domain;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;
    private final CourseRepository courseRepository;
    private final TagRepository tagRepository;

    public ProblemService(ProblemRepository problemRepository, CourseRepository courseRepository, TagRepository tagRepository) {
        this.problemRepository = problemRepository;
        this.tagRepository = tagRepository;
        this.courseRepository = courseRepository;
    }

    public List<Problem> getProblems() {
        return problemRepository.findAll();
    }

    public List<ProblemWithMetadata> getProblemsWithMetadata() {
        return getProblems().stream()
            .map(problem -> {
                Course course = courseRepository.findByProblemId(problem.getId());
                List<Tag> tags = tagRepository.findAllByProblemId(problem.getId());
                return new ProblemWithMetadata(problem, course, tags);
            }).toList();
    }
}
