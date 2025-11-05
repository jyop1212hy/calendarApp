package com.calendarapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyCalenderRequest {

    private String title;
    private String authorName;
    private String password;
}
