package com.projekt.ems.Services;

import com.projekt.ems.Dto.BookDto;
import java.util.List;




public interface BookService {

    List<BookDto> findAllBooks();
    BookDto findBookById(Long id);
    BookDto saveBook(BookDto book);
    BookDto updateBook(Long id, BookDto book);
    void deleteBook(Long id);


}