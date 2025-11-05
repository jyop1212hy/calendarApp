package com.calendarapp.dto;

import lombok.Getter;

@Getter
public class CreateCalendarRequest {

    private String title;
    private String detail;
    private String authorName;
    private String password;

    public CreateCalendarRequest(String title, String detail, String authorName, String password) {
        this.title = title;
        this.detail = detail;
        this.authorName = authorName;
        this.password = password;
    }

}
