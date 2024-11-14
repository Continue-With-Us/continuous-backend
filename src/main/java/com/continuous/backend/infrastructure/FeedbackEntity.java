package com.continuous.backend.infrastructure;

import com.continuous.backend.domain.Feedback;

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
@Table(name = "feedback")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedbackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    public FeedbackEntity(String content) {
        this.content = content;
    }

    public static FeedbackEntity from(Feedback feedback) {
        return new FeedbackEntity(feedback.getContent());
    }

    public Feedback toFeedback() {
        return new Feedback(id, content);
    }
}
