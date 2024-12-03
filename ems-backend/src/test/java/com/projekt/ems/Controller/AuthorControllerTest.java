package com.projekt.ems.Controller;

import com.projekt.ems.Dto.AuthorDto;
import com.projekt.ems.Models.Author;
import com.projekt.ems.Repositories.AuthorRepository;
import com.projekt.ems.Services.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class AuthorControllerTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;

    @Test
    public void testCreateAuthor() {
        Author author = new Author();
        author.setName("Adam");
        author.setSurname("Mickiewicz");

        AuthorDto authorDto = new AuthorDto(author);

        AuthorDto saveAuthorDto = authorService.saveAuthor(authorDto);

        Author findAuthor = authorRepository.findById(saveAuthorDto.getId()).orElse(null);
        assertNotNull(findAuthor);
        assertEquals("Adam", findAuthor.getName());
        assertEquals("Mickiewicz", findAuthor.getSurname());
    }
}
