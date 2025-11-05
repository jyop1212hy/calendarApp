package com.calendarapp.dto;

import lombok.Getter;

@Getter
public class ModifyCalenderRequest {

    public ModifyCalenderRequest(String title, String authorName, String password) {
        this.title = title;
        this.authorName = authorName;
        this.password = password;
    }

    private String title;
    private String authorName;
    private String password;
}
