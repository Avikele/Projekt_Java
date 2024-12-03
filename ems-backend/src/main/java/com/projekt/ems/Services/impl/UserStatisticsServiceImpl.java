package com.projekt.ems.Services.impl;

import com.projekt.ems.Dto.ExportDto;
import com.projekt.ems.Dto.ReadingSessionDto;
import com.projekt.ems.Dto.UserBookDto;
import com.projekt.ems.Dto.UserStatisticsDto;
import com.projekt.ems.Models.*;
import com.projekt.ems.Repositories.UserBookRepository;
import com.projekt.ems.Repositories.UserStatisticsRepository;
import com.projekt.ems.Services.ReadingSessionService;
import com.projekt.ems.Services.UserBookService;
import com.projekt.ems.Services.UserService;
import com.projekt.ems.Services.UserStatisticsService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class UserStatisticsServiceImpl implements UserStatisticsService {

    private final UserStatisticsRepository userStatisticsRepository;
    private final UserBookService userBookService;
    private final UserBookRepository userBookRepository;
    private final ReadingSessionService readingSessionService;
    private final UserService userService;

    public UserStatisticsServiceImpl(UserStatisticsRepository userStatisticsRepository, UserBookService userBookService, UserBookRepository userBookRepository, UserService userService, @Lazy ReadingSessionService readingSessionService) {
        this.userStatisticsRepository = userStatisticsRepository;
        this.userBookService = userBookService;
        this.userBookRepository = userBookRepository;
        this.userService = userService;
        this.readingSessionService = readingSessionService;
    }

    @Override
    public UserStatisticsDto getUserStatistics(Long id) {
        UserStatistics userStatistics = userStatisticsRepository.findById(id).orElseThrow(() -> new RuntimeException("User statistics not found"));
        return new UserStatisticsDto(userStatistics.getId(), userStatistics.getPagesRead(), userStatistics.getTime(), userStatistics.getReadDate());
    }

    @Override
    public UserStatisticsDto getOrCrateUserStatistics(Long userId, Long bookId) {
        UserBookDto userBookDto = userBookService.getOrCreateUserBook(userId, bookId);
        UserBook userBook = userBookRepository.findById(userBookDto.getId()).orElseThrow(() -> new RuntimeException("User book not found"));
        UserStatistics userStatistics = userStatisticsRepository.findById(userBook.getId()).orElse(null);
        if(userStatistics == null) {
            userStatistics = new UserStatistics();
            userStatistics.setUserBook(userBook);
            userStatistics.setPagesRead(0);
            userStatistics.setTime(LocalTime.MIN);
            userStatistics.setReadDate(null);
            UserStatistics saveUserStatistics = userStatisticsRepository.save(userStatistics);
            return new UserStatisticsDto(saveUserStatistics);
        }
        return new UserStatisticsDto(userStatistics);
    }

    @Override
    public ExportDto exportUserStatistics(Long id) {
        UserStatistics userStatistics = userStatisticsRepository.findById(id).orElseThrow(() -> new RuntimeException("User statistics not found"));
        List<ReadingSessionDto> sessions = readingSessionService.getAllReadingSession(id);
        return new ExportDto(userStatistics, sessions);
    }

    @Override
    public void importUserStatistics(Long userId, Long bookId, ExportDto exportDto) {
        UserStatisticsDto userStatisticsDto = this.getOrCrateUserStatistics(userId, bookId);
        UserStatistics userStatistics = userStatisticsRepository.findById(userStatisticsDto.getUser_book_id()).orElseThrow(() -> new RuntimeException("User statistics not found"));
        if(userStatistics.getPagesRead() > 0) {
            throw new RuntimeException("User statistics are not empty. Can not import new one.");
        }
        userStatistics.setPagesRead(exportDto.getReadPages());
        userStatistics.setTime(exportDto.getReadingTime());
        readingSessionService.importReadingSessions(exportDto.getSessions(), userStatistics);
        userStatisticsRepository.save(userStatistics);
    }

    @Override
    public UserStatisticsDto updateUserStatistics(Long id, UserStatisticsDto userStatisticsDto) {
        UserStatistics userStatistics = userStatisticsRepository.findById(id).orElseThrow(() -> new RuntimeException("User statistics not found"));
        userStatistics.setReadDate(userStatisticsDto.getRead_date());
        userStatistics.setTime(userStatisticsDto.getTime());
        userStatistics.setPagesRead(userStatisticsDto.getPages_read());
        UserStatistics saveUserStatistics = userStatisticsRepository.save(userStatistics);
        return new UserStatisticsDto(saveUserStatistics);
    }

    @Override
    public void calculateUserStatistics(ReadingSession readingSession) {
        UserStatistics userStatistics = readingSession.getUserStatistics();
        User user = userStatistics.getUserBook().getUser();
        userStatistics.setTime(userStatistics.getTime().plusHours(readingSession.getTime().getHour())
                .plusMinutes(readingSession.getTime().getMinute())
                .plusSeconds(readingSession.getTime().getSecond()));
        userStatistics.setPagesRead(userStatistics.getPagesRead() + readingSession.getPages());
        Book book = userStatistics.getUserBook().getBook();
        if(book.getPages() < userStatistics.getPagesRead()) {
            throw new RuntimeException("Too many pages");
        }
        if(book.getPages() > userStatistics.getPagesRead()) {
            userBookService.updateUserBookStatus(userStatistics.getUserBook().getId(), 2);
            userStatistics.setReadDate(null);
        }
        else {
            userBookService.updateUserBookStatus(userStatistics.getUserBook().getId(), 3);
            userStatistics.setReadDate(LocalDate.now());
            userService.addReadBook(user);
        }
        userStatisticsRepository.save(userStatistics);
        userService.addTime(readingSession.getTime(), user);
        userService.addPagesRead(readingSession.getPages(), user);
    }
    @Override
    public void returnUserStatistics(ReadingSession readingSession) {
        UserStatistics userStatistics = readingSession.getUserStatistics();
        User user = userStatistics.getUserBook().getUser();
        userStatistics.setTime(userStatistics.getTime().minusHours(readingSession.getTime().getHour())
                .minusMinutes(readingSession.getTime().getMinute())
                .minusSeconds(readingSession.getTime().getSecond()));
        userStatistics.setPagesRead(userStatistics.getPagesRead() - readingSession.getPages());

        Book book = userStatistics.getUserBook().getBook();
        if(book.getPages() > userStatistics.getPagesRead()) {
            if(userStatistics.getReadDate() != null) {
                userService.removeReadBook(user);
            }
            userBookService.updateUserBookStatus(userStatistics.getUserBook().getId(), 2);
            userStatistics.setReadDate(null);
        }
        userStatisticsRepository.save(userStatistics);
        userService.removeTime(readingSession.getTime(), user);
        userService.removePagesRead(readingSession.getPages(), user);
    }

}
