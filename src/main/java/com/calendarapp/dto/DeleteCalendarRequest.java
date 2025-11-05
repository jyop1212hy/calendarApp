package com.calendarapp.dto;

import lombok.Getter;

@Getter
public class DeleteCalendarRequest {

    public DeleteCalendarRequest(String password) {
        this.password = password;
    }
    private String password;
}
