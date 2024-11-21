package com.projekt.ems.Dto;

import com.projekt.ems.Models.ReadingSession;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ReadingSessionDto {
    private long id;
    private long user_statistics_id;
    private int pages;
    private LocalTime time;

    public ReadingSessionDto(ReadingSession readingSession) {
        this.id = readingSession.getId();
        this.user_statistics_id = readingSession.getUserStatistics().getId();
        this.time = readingSession.getTime();
        this.pages = readingSession.getPages();
    }

    public ReadingSessionDto() {

    }
}
