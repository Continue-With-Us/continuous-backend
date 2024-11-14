package com.continuous.backend.domain;

import java.util.ArrayList;
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

    public List<ProblemWithMetadata> getProblemsWithMetadataFiltered(String course, List<String> tags) {
        List<ProblemWithMetadata> problems = getProblemsWithMetadata();

        List<Tag> filteringTags;
        if (tags != null) {
            filteringTags = tags.stream().map(Tag::from).toList();
        } else {
            filteringTags = new ArrayList<>();
        }

        return problems.stream()
            .filter(problem -> {
                boolean courseMatches = (course == null) || problem.getCourse().equals(Course.from(course));

                boolean tagsMatch = filteringTags.isEmpty() ||
                    problem.getTags().stream().anyMatch(filteringTags::contains);

                return courseMatches && tagsMatch;
            })
            .toList();
    }

    public ProblemWithMetadata getProblem(long problemId) {
        Problem problem = problemRepository.findById(problemId);
        Course course = courseRepository.findByProblemId(problemId);
        List<Tag> tags = tagRepository.findAllByProblemId(problemId);

        return new ProblemWithMetadata(problem, course, tags);
    }
}
