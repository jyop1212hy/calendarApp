package com.calendarapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyCalenderRequest {

    private String title;
    private String authorName;
    private String password;

    public ModifyCalenderRequest(String title, String authorName, String password){
        this.title = title;
        this.authorName = authorName;
        this.password = password;
    }
}
