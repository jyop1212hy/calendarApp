package com.calendarapp.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCalenderRequest {

    private String title;
    private String detail;
    private String authorName;
    private String password;
}
