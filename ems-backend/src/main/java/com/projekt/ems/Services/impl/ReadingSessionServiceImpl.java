package com.projekt.ems.Services.impl;

import com.projekt.ems.Dto.ReadingSessionDto;
import com.projekt.ems.Dto.UserStatisticsDto;
import com.projekt.ems.Models.ReadingSession;
import com.projekt.ems.Models.UserStatistics;
import com.projekt.ems.Repositories.ReadingSessionRepository;
import com.projekt.ems.Repositories.UserStatisticsRepository;
import com.projekt.ems.Services.ReadingSessionService;
import com.projekt.ems.Services.UserStatisticsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReadingSessionServiceImpl implements ReadingSessionService {

    private ReadingSessionRepository readingSessionRepository;
    private UserStatisticsService userStatisticsService;
    private UserStatisticsRepository userStatisticsRepository;

    public  ReadingSessionServiceImpl(ReadingSessionRepository readingSessionRepository, UserStatisticsService userStatisticsService, UserStatisticsRepository userStatisticsRepository) {
        this.readingSessionRepository = readingSessionRepository;
        this.userStatisticsService = userStatisticsService;
        this.userStatisticsRepository = userStatisticsRepository;
    }


    @Override
    public ReadingSessionDto getReadingSessionById(Long id) {
        ReadingSession readingSession = readingSessionRepository.findById(id).orElseThrow(() -> new RuntimeException("Reading session not found"));
        return new ReadingSessionDto(readingSession);
    }

    @Override
    public List<ReadingSessionDto> getAllReadingSession(Long userStatisticsId) {
        List<ReadingSession> readingSessions = readingSessionRepository.getAllReadingSession(userStatisticsId);
        List<ReadingSessionDto> readingSessionDtos = new ArrayList<ReadingSessionDto>();
        readingSessions.forEach((r) -> {
            readingSessionDtos.add(new ReadingSessionDto(r));
        });
        return readingSessionDtos;
    }

    @Override
    public ReadingSessionDto crateReadingSession(ReadingSessionDto readingSessionDto, Long bookId, Long userId) {
        UserStatisticsDto userStatisticsDto = userStatisticsService.getOrCrateUserStatistics(userId, bookId);
        UserStatistics userStatistics = userStatisticsRepository.findById(userStatisticsDto.getUser_book_id()).orElseThrow(() -> new RuntimeException("User statistics not found"));
        ReadingSession readingSession = new ReadingSession();
        readingSession.setUserStatistics(userStatistics);
        readingSession.setTime(readingSessionDto.getTime());
        readingSession.setPages(readingSessionDto.getPages());
        ReadingSession saveReadingSession = readingSessionRepository.save(readingSession);
        userStatisticsService.calculateUserStatistics(saveReadingSession);
        return new ReadingSessionDto(saveReadingSession);
    }

    @Override
    public void importReadingSessions(List<ReadingSessionDto> readingSessions, UserStatistics userStatistics) {
        readingSessions.forEach((readingSessionDto -> {
            ReadingSession newReadingSession = new ReadingSession();
            newReadingSession.setUserStatistics(userStatistics);
            newReadingSession.setTime(readingSessionDto.getTime());
            newReadingSession.setPages(readingSessionDto.getPages());
            readingSessionRepository.save(newReadingSession);
        }));
    }

    @Override
    public ReadingSessionDto updateReadingSession(Long id, ReadingSessionDto readingSessionDto) {
        ReadingSession readingSession = readingSessionRepository.findById(id).orElseThrow(() -> new RuntimeException("Reading session not found"));
        userStatisticsService.returnUserStatistics(readingSession);
        readingSession.setPages(readingSessionDto.getPages());
        readingSession.setTime(readingSessionDto.getTime());
        ReadingSession saveReadingSession = readingSessionRepository.save(readingSession);
        userStatisticsService.calculateUserStatistics(saveReadingSession);
        return new ReadingSessionDto(saveReadingSession);
    }

    @Override
    public void deleteReadingSession(Long id) {
        ReadingSession readingSession = readingSessionRepository.findById(id).orElseThrow(() -> new RuntimeException("Reading session not found"));
        userStatisticsService.returnUserStatistics(readingSession);
        readingSessionRepository.delete(readingSession);
    }
}
