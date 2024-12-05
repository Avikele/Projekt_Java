package com.projekt.ems.Controllers;

import com.projekt.ems.Dto.BookDto;
import com.projekt.ems.Models.Book;
import com.projekt.ems.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> ListBooks() {
        List<BookDto> books = bookService.findAllBooks();
        return ResponseEntity.ok(books);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findBookById(@PathVariable Long id) {
        BookDto book = bookService.findBookById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/")
    public ResponseEntity<BookDto> saveBook(@RequestBody BookDto bookDto) {
        BookDto book = bookService.saveBook(bookDto);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto, @RequestAttribute("privilages") int privilages) {
        if(privilages == 1) {
            BookDto book = bookService.updateBook(id, bookDto);
            return ResponseEntity.ok(book);
        }
        else{
            return ResponseEntity.status(402).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

}
