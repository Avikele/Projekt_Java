package com.projekt.ems.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projekt.ems.Models.UserStatistics;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class ExportDto {
    private String title;
    private Integer bookPages;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime readingTime;
    private Integer readPages;
    private LocalDate readDate;
    private List<ReadingSessionDto> sessions;

    public ExportDto(UserStatistics userStatistics, List<ReadingSessionDto> sessions) {
        this.title = userStatistics.getUserBook().getBook().getTitle();
        this.bookPages = userStatistics.getUserBook().getBook().getPages();
        this.readingTime = userStatistics.getTime();
        this.readPages = userStatistics.getPagesRead();
        this.readDate = userStatistics.getReadDate();
        this.sessions = sessions;

    }

    public ExportDto() {

    }
}
