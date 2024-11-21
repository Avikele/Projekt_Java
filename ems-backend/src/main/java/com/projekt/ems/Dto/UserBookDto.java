package com.projekt.ems.Dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserBookDto {
    private long id;
    private long user_id;
    private long book_id;
    private int status;
}
