package com.calendarapp.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetAllCalenderResponse {

    private final Long id;
    private final String title;
    private final String detail;
    private final String authorName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetAllCalenderResponse(Long id, String title, String detail, String authorName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
