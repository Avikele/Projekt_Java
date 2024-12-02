package com.projekt.ems.Dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RatingDto {
    private long user_book_id;
    private int score;

    public RatingDto(long user_book_id, int score) {
        this.user_book_id = user_book_id;
        this.score = score;
    }
}
