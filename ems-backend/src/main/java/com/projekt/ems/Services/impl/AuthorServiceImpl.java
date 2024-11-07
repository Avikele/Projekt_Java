package com.projekt.ems.Services.impl;

import com.projekt.ems.Dto.AuthorDto;
import com.projekt.ems.Dto.BookDto;
import com.projekt.ems.Models.Author;
import com.projekt.ems.Models.Book;
import com.projekt.ems.Repositories.AuthorRepository;
import com.projekt.ems.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDto> findAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(this::mapToAuthorDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDto findAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        return mapToAuthorDto(author);
    }

    @Override
    public AuthorDto saveAuthor(AuthorDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        Author savedAuthor = authorRepository.save(author);
        return mapToAuthorDto(savedAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public AuthorDto updateAuthor(Long id, AuthorDto authorDto) {
        Author author = authorRepository.findById(id).orElse(null);
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        Author updatedAuthor = authorRepository.save(author);
        return mapToAuthorDto(updatedAuthor);
    }

    private AuthorDto mapToAuthorDto(Author author) {
        if (author == null) {
            return null;
        }
        return AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }
}
