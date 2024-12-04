package com.projekt.ems.Services;

import com.projekt.ems.Dto.ReadingSessionDto;
import com.projekt.ems.Models.UserStatistics;

import java.util.List;

public interface ReadingSessionService {
    ReadingSessionDto getReadingSessionById(Long id);
    List<ReadingSessionDto> getAllReadingSession(Long userStatisticsId);
    ReadingSessionDto crateReadingSession(ReadingSessionDto readingSessionDto, Long bookId, Long userId);
    void importReadingSessions(List<ReadingSessionDto> readingSessions, UserStatistics userStatistics);
    ReadingSessionDto updateReadingSession(Long id, ReadingSessionDto readingSessionDto);
    void deleteReadingSession(Long id);
}
