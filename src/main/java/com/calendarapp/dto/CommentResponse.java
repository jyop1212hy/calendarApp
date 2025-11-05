package com.calendarapp.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private final Long id;
    private final String comment;
    private final String authorName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CommentResponse(Long id, String comment, String authorName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.comment = comment;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
