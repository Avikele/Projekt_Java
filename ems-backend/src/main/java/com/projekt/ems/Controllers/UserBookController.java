package com.projekt.ems.Controllers;

import com.projekt.ems.Dto.UserBookDto;
import com.projekt.ems.Services.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/userbooks")
public class UserBookController {

    private UserBookService userBookService;

    @Autowired
    public UserBookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }

    @GetMapping("/")
    public ResponseEntity<UserBookDto> getUserBook(@RequestParam Long user_id, @RequestParam Long book_id) {
        UserBookDto userBook = userBookService.getOrCreateUserBook(user_id, book_id);
        if (userBook == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(userBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserBookDto> findUserBookById(@PathVariable Long id) {
        UserBookDto userBook = userBookService.findUserBookById(id);
        if (userBook == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(userBook);
    }

    @PostMapping("/")
    public ResponseEntity<UserBookDto> createUserBook(@RequestBody UserBookDto userBookDto) {
        UserBookDto userBook = userBookService.saveUserBook(userBookDto);
        return ResponseEntity.ok(userBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserBookDto> updateUserBook(@PathVariable Long id, @RequestParam int status) {
        UserBookDto userBookDto = userBookService.updateUserBookStatus(id, status);
        return  ResponseEntity.ok(userBookDto);
    }
}
