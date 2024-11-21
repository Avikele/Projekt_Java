package com.projekt.ems.Services;

import com.projekt.ems.Dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> findAllAuthors();
    AuthorDto findAuthorById(Long id);
    AuthorDto saveAuthor(AuthorDto author);
    AuthorDto updateAuthor(Long id, AuthorDto author);
    void deleteAuthor(Long id);
}
