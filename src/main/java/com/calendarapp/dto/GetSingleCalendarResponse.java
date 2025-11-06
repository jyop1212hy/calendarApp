package com.calendarapp.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetSingleCalendarResponse {

    private final Long id;
    private final String title;
    private final String detail;
    private final String authorName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<CommentResponse> comments;

    public GetSingleCalendarResponse(Long id, String title, String detail, String authorName, LocalDateTime createdAt, LocalDateTime modifiedAt, List<CommentResponse> comments) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.comments = comments;
    }
}
