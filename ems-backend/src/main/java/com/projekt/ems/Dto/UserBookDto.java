package com.projekt.ems.Dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserBookDto {
    private long id;
    private long user_id;
    private long book_id;
    private Integer status;

    public UserBookDto(Long id, Long user_id, Long book_id, Integer status) {
        this.id = id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.status = status;
    }
}
