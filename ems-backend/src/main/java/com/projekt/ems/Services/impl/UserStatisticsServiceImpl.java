package com.projekt.ems.Services.impl;

import com.projekt.ems.Dto.UserBookDto;
import com.projekt.ems.Dto.UserStatisticsDto;
import com.projekt.ems.Models.Book;
import com.projekt.ems.Models.ReadingSession;
import com.projekt.ems.Models.UserBook;
import com.projekt.ems.Models.UserStatistics;
import com.projekt.ems.Repositories.UserBookRepository;
import com.projekt.ems.Repositories.UserStatisticsRepository;
import com.projekt.ems.Services.UserBookService;
import com.projekt.ems.Services.UserStatisticsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class UserStatisticsServiceImpl implements UserStatisticsService {

    private final UserStatisticsRepository userStatisticsRepository;
    private final UserBookService userBookService;
    private final UserBookRepository userBookRepository;

    public UserStatisticsServiceImpl(UserStatisticsRepository userStatisticsRepository, UserBookService userBookService, UserBookRepository userBookRepository) {
        this.userStatisticsRepository = userStatisticsRepository;
        this.userBookService = userBookService;
        this.userBookRepository = userBookRepository;
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
        userStatistics.setTime(userStatistics.getTime().plusHours(readingSession.getTime().getHour())
                .plusMinutes(readingSession.getTime().getMinute())
                .plusSeconds(readingSession.getTime().getSecond()));
        userStatistics.setPagesRead(userStatistics.getPagesRead() + readingSession.getPages());
        Book book = userStatistics.getUserBook().getBook();
        if(book.getPages() > userStatistics.getPagesRead()) {
            userBookService.updateUserBookStatus(userStatistics.getUserBook().getId(), 2);
            userStatistics.setReadDate(null);
        }
        else {
            userBookService.updateUserBookStatus(userStatistics.getUserBook().getId(), 3);
            userStatistics.setReadDate(LocalDate.now());
        }
        userStatisticsRepository.save(userStatistics);
    }
    @Override
    public void returnUserStatistics(ReadingSession readingSession) {
        UserStatistics userStatistics = readingSession.getUserStatistics();
        userStatistics.setTime(userStatistics.getTime().minusHours(readingSession.getTime().getHour())
                .minusMinutes(readingSession.getTime().getMinute())
                .minusSeconds(readingSession.getTime().getSecond()));
        userStatistics.setPagesRead(userStatistics.getPagesRead() - readingSession.getPages());

        Book book = userStatistics.getUserBook().getBook();
        if(book.getPages() > userStatistics.getPagesRead()) {
            userBookService.updateUserBookStatus(userStatistics.getUserBook().getId(), 2);
            userStatistics.setReadDate(null);
        }
        else {
            userBookService.updateUserBookStatus(userStatistics.getUserBook().getId(), 3);
            userStatistics.setReadDate(LocalDate.now());
        }
        userStatisticsRepository.save(userStatistics);
    }

}
