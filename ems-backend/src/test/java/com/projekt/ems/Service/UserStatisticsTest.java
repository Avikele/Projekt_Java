package com.projekt.ems.Service;

import com.projekt.ems.Models.*;
import com.projekt.ems.Repositories.BookRepository;
import com.projekt.ems.Repositories.UserRepository;
import com.projekt.ems.Repositories.UserStatisticsRepository;
import com.projekt.ems.Services.impl.BookServiceImpl;
import com.projekt.ems.Services.impl.UserBookServiceImpl;
import com.projekt.ems.Services.impl.UserStatisticsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserStatisticsTest {

    @Mock
    private UserStatisticsRepository userStatisticsRepository;

    @Mock
    private UserBookServiceImpl userBookService;

    @InjectMocks
    private UserStatisticsServiceImpl userStatisticsService;

    @Test
    public void testcalculateUserStatistics() {
        User user = new User();
        user.setTime(LocalTime.of(1, 0, 22));
        user.setPagesRead(100);

        Book book = new Book();
        book.setPages(100);

        UserBook userBook = new UserBook();
        userBook.setUser(user);
        userBook.setBook(book);

        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setTime(LocalTime.of(0,30,0));
        userStatistics.setPagesRead(49);
        userStatistics.setUserBook(userBook);

        ReadingSession readingSession = new ReadingSession();
        readingSession.setUserStatistics(userStatistics);
        readingSession.setTime(LocalTime.of(0, 15, 38));
        readingSession.setPages(21);

        userStatisticsService.calculateUserStatistics(readingSession);

        assertEquals(70, userStatistics.getPagesRead());
        assertEquals(LocalTime.of(0,45, 38), userStatistics.getTime());
        assertNull(userStatistics.getReadDate());
        verify(userStatisticsRepository).save(userStatistics);
    }

    @Test
    public void testcalculateUserStatisticsWhenBookIsRead() {
        User user = new User();
        user.setTime(LocalTime.of(1, 0, 22));
        user.setPagesRead(100);

        Book book = new Book();
        book.setPages(100);

        UserBook userBook = new UserBook();
        userBook.setUser(user);
        userBook.setBook(book);

        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setTime(LocalTime.of(0,30,0));
        userStatistics.setPagesRead(49);
        userStatistics.setUserBook(userBook);

        ReadingSession readingSession = new ReadingSession();
        readingSession.setUserStatistics(userStatistics);
        readingSession.setTime(LocalTime.of(0, 15, 38));
        readingSession.setPages(51);

        userStatisticsService.calculateUserStatistics(readingSession);

        assertEquals(100, userStatistics.getPagesRead());
        assertEquals(LocalTime.of(0,45, 38), userStatistics.getTime());
        assertNotNull(userStatistics.getReadDate());
        verify(userStatisticsRepository).save(userStatistics);
    }

}
