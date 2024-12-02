package com.projekt.ems.Services;

import com.projekt.ems.Dto.UserStatisticsDto;
import com.projekt.ems.Models.ReadingSession;
import com.projekt.ems.Models.UserStatistics;

public interface UserStatisticsService {
    UserStatisticsDto getUserStatistics(Long id);
    UserStatisticsDto getOrCrateUserStatistics(Long userId, Long bookId);
    UserStatisticsDto updateUserStatistics(Long id, UserStatisticsDto userStatisticsDto);
    void calculateUserStatistics(ReadingSession readingSession);
    void returnUserStatistics(ReadingSession readingSession);
}
