package com.projekt.ems.Dto;

import com.projekt.ems.Models.UserStatistics;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class UserStatisticsDto {
    private long user_book_id;
    private int pages_read;
    private LocalTime time;
    private LocalDate read_date;

    public UserStatisticsDto(long user_book_id, int pages_read, LocalTime time, LocalDate read_date) {
        this.user_book_id = user_book_id;
        this.pages_read = pages_read;
        this.time = time;
        this.read_date = read_date;
    }

    public UserStatisticsDto(UserStatistics userStatistics) {
        this.user_book_id = userStatistics.getId();
        this.pages_read = userStatistics.getPagesRead();
        this.time = userStatistics.getTime();
        this.read_date = userStatistics.getReadDate();
    }
}
