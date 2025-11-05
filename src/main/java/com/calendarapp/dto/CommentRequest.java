package com.calendarapp.dto;

import lombok.Getter;

@Getter
public class CommentRequest {

    private String comment;
    private String authorName;
    private String password;

    public CommentRequest(String comment, String authorName, String password) {
        this.comment = comment;
        this.authorName = authorName;
        this.password = password;
    }
}

