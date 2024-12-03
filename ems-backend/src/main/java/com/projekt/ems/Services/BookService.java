package com.projekt.ems.Services;

import com.projekt.ems.Dto.BookDto;
import com.projekt.ems.Models.Book;

import java.util.List;




public interface BookService {

    List<BookDto> findAllBooks();
    BookDto findBookById(Long id);
    BookDto saveBook(BookDto book);
    BookDto updateBook(Long id, BookDto book);
    void deleteBook(Long id);
    void addRating(Integer rating, Book book);
    void removeRating(Integer rating, Book book);

}