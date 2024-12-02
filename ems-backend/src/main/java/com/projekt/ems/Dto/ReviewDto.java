package com.projekt.ems.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDto {
    private long user_book_id;
    private int status;
    private String text;
    private String username;

    public ReviewDto(long user_book_id, int status, String text, String username) {
        this.user_book_id = user_book_id;
        this.status = status;
        this.text = text;
        this.username = username;
    }

}
