package com.calendarapp.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CalenderResponse {

    private Long id;
    private String title;
    private String detail;
    private String authorName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CalenderResponse(Long id, String title, String detail, String authorName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}