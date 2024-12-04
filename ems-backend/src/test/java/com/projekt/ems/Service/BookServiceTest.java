package com.projekt.ems.Service;

import com.projekt.ems.Models.Book;
import com.projekt.ems.Repositories.BookRepository;
import com.projekt.ems.Services.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void testAddRating() {
        Book book = new Book();
        book.setAvgRating(4.0);
        book.setCountRating(2);

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

        int newRating = 6;

        assertThrows(RuntimeException.class, () -> bookService.addRating(newRating, book));
    }

    @Test
    public void testOutOfRangeRemovingRating() {
        Book book = new Book();
        book.setAvgRating(4.333);
        book.setCountRating(3);

        int newRating = 0;

        assertThrows(RuntimeException.class, () -> bookService.removeRating(newRating, book));
    }

}
