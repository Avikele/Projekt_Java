package com.projekt.ems.Controllers;

import com.projekt.ems.Dto.AuthorDto;
import com.projekt.ems.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    private AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping("/")
    public ResponseEntity<List<AuthorDto>> findAllAuthors() {
        List<AuthorDto> authors = authorService.findAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> findAuthorById(@PathVariable Long id) {
        AuthorDto author = authorService.findAuthorById(id);
        return ResponseEntity.ok(author);
    }

    @PostMapping("/")
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody AuthorDto author) {
        AuthorDto authorDto = authorService.saveAuthor(author);
        return ResponseEntity.ok(authorDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id, @RequestBody AuthorDto author) {
        AuthorDto authorDto = authorService.updateAuthor(id, author);
        return ResponseEntity.ok(authorDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }


}
