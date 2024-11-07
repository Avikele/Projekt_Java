package com.projekt.ems.Services;


import com.projekt.ems.Models.Author;
import com.projekt.ems.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {this.authorRepository = authorRepository;}

    public List<Author> GetAllAuthors() {return authorRepository.findAll(); }

    public Optional<Author> GetAuthorById(Long id) { return authorRepository.findById(id);}

    public Author AddAuthor(Author author) { return authorRepository.save(author); }

    public Author UpdateAuthor(Author author) { return authorRepository.save(author); }

    public void DeleteAuthor(Long id) { authorRepository.deleteById(id); }


}
