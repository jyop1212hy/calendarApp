package com.calendarapp.dto;

import lombok.Getter;

@Getter
public class DeleteCalenderRequest {

    public DeleteCalenderRequest(String password) {
        this.password = password;
    }
    private String password;
}
