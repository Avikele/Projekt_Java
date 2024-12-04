package com.projekt.ems;

import com.projekt.ems.Models.*;
import com.projekt.ems.Repositories.BookRepository;
import com.projekt.ems.Repositories.PublisherRepository;
import com.projekt.ems.Repositories.UserStatisticsRepository;
import com.projekt.ems.Services.UserService;
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
public class UnitTests {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private PublisherRepository publisherRepository;

    @Mock
    private UserStatisticsRepository userStatisticsRepository;

    @Mock
    private UserBookServiceImpl userBookService;

    @Mock
    private UserService userService;

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

    @Test
    public void testReturnUserStatistics() {
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
        readingSession.setPages(19);

        userStatisticsService.returnUserStatistics(readingSession);

        assertEquals(30, userStatistics.getPagesRead());
        assertEquals(LocalTime.of(0,14, 22), userStatistics.getTime());
        assertNull(userStatistics.getReadDate());
        verify(userStatisticsRepository).save(userStatistics);
    }

    @Test
    public void testAddRating() {
        Book book = new Book();
        book.setAvgRating(4.0);
        book.setCountRating(2);
        book.setSumRating(8);

        int newRating = 5;

        bookService.addRating(newRating, book);

        assertEquals(4.333, book.getAvgRating(), 0.001);
        verify(bookRepository).save(book);
    }

    @Test
    public void testRemoveRating() {
        Book book = new Book();
        book.setAvgRating(4.333);
        book.setCountRating(3);
        book.setSumRating(13);

        int removedRating = 5;

        bookService.removeRating(removedRating, book);

        assertEquals(4.0, book.getAvgRating(), 0.001);
        verify(bookRepository).save(book);
    }

    @Test
    public void testOutOfRangeAddRating() {
        Book book = new Book();
        book.setAvgRating(4.333);
        book.setCountRating(3);
        book.setSumRating(13);

        int newRating = 6;

        assertThrows(RuntimeException.class, () -> bookService.addRating(newRating, book));
    }

    @Test
    public void testOutOfRangeRemovingRating() {
        Book book = new Book();
        book.setAvgRating(4.333);
        book.setCountRating(3);
        book.setSumRating(13);

        int newRating = 0;

        assertThrows(RuntimeException.class, () -> bookService.removeRating(newRating, book));
    }

    @Test void testRemoveRatingWhenIsOneRating() {
        Book book = new Book();
        book.setAvgRating(3.0);
        book.setCountRating(1);
        book.setSumRating(3);

        int removedRating = 5;

        bookService.removeRating(removedRating, book);

        assertEquals(0.0, book.getAvgRating(), 0.001);
        verify(bookRepository).save(book);
    }
}
