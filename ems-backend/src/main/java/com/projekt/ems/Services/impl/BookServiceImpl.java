package com.projekt.ems.Services.impl;

import com.projekt.ems.Dto.BookDto;
import com.projekt.ems.Models.Author;
import com.projekt.ems.Models.Book;
import com.projekt.ems.Models.Category;
import com.projekt.ems.Models.Publisher;
import com.projekt.ems.Repositories.AuthorRepository;
import com.projekt.ems.Repositories.BookRepository;
import com.projekt.ems.Repositories.CategoryRepository;
import com.projekt.ems.Repositories.PublisherRepository;
import com.projekt.ems.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, PublisherRepository publisherRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<BookDto> findAllBooks() {
        List<Book> Books = bookRepository.findAll();
        return Books.stream().map((book) -> mapToBookDto(book)).collect(Collectors.toList());
    }

    @Override
    public BookDto findBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        return mapToBookDto(book);
    }

    @Override
    public BookDto saveBook(BookDto bookDto) {

        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setPages(bookDto.getPages());
        book.setCover(bookDto.getCover());
        book.setStatus(bookDto.isStatus());
        book.setAvgRating(bookDto.getAvgRating());

        if (bookDto.getPublisher() != null) {
            Publisher publisher = publisherRepository.findByName(bookDto.getPublisher())
                    .orElseGet(() -> {

                        Publisher newPublisher = new Publisher();
                        newPublisher.setName(bookDto.getPublisher());
                        return publisherRepository.save(newPublisher);
                    });
            book.setPublisher(publisher);
        } else {
            throw new RuntimeException("Publisher cannot be null");
        }

        if (bookDto.getAuthors() != null && !bookDto.getAuthors().isEmpty()) {
            Set<Author> authors = bookDto.getAuthors().stream()
                    .map(authorName -> authorRepository.findByName(authorName)
                            .orElseThrow(() -> new RuntimeException("Author not found: " + authorName)))
                    .collect(Collectors.toSet());
            book.setAuthors(authors);
        }

        if (bookDto.getCategories() != null && !bookDto.getCategories().isEmpty()) {
            Set<Category> categories = bookDto.getCategories().stream()
                    .map(categoryName -> {

                        return categoryRepository.findByName(categoryName)
                                .orElseGet(() -> {

                                    Category newCategory = new Category();
                                    newCategory.setName(categoryName);
                                    return categoryRepository.save(newCategory);
                                });
                    })
                    .collect(Collectors.toSet());
            book.setCategories(categories);
        }

        Book savedBook = bookRepository.save(book);

        return mapToBookDto(savedBook);
    }

    @Transactional
    @Override
    public BookDto updateBook(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found: " + id));
        book.setTitle(bookDto.getTitle());
        book.setPages(bookDto.getPages());
        book.setCover(bookDto.getCover());
        book.setStatus(bookDto.isStatus());
        book.setAvgRating(bookDto.getAvgRating());

        if (bookDto.getPublisher() != null) {
            Publisher publisher = publisherRepository.findByName(bookDto.getPublisher())
                    .orElseGet(() -> {
                        Publisher newPublisher = new Publisher();
                        newPublisher.setName(bookDto.getPublisher());
                        return publisherRepository.save(newPublisher);
                    });
            book.setPublisher(publisher);
        } else {
            throw new RuntimeException("Publisher cannot be null");
        }

        if (bookDto.getAuthors() != null && !bookDto.getAuthors().isEmpty()) {
            Set<Author> authors = bookDto.getAuthors().stream()
                    .map(authorName -> authorRepository.findByName(authorName)
                            .orElseThrow(() -> new RuntimeException("Author not found: " + authorName)))
                    .collect(Collectors.toSet());
            book.setAuthors(authors);
        }

        if (bookDto.getCategories() != null && !bookDto.getCategories().isEmpty()) {
            Set<Category> categories = bookDto.getCategories().stream()
                    .map(categoryName -> {

                        return categoryRepository.findByName(categoryName)
                                .orElseGet(() -> {

                                    Category newCategory = new Category();
                                    newCategory.setName(categoryName);
                                    return categoryRepository.save(newCategory);
                                });
                    })
                    .collect(Collectors.toSet());
            book.setCategories(categories);
        }

        Book updatedBook = bookRepository.save(book);
        return mapToBookDto(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void addRating(Integer rating, Book book) {
        if(rating < 1 || rating > 5) {
            throw new RuntimeException("Wrong rating!");
        }
        book.setAvgRating((double)(book.getAvgRating() * book.getCountRating() + rating) / (book.getCountRating() + 1));
        bookRepository.save(book);
    }

    @Override
    public void removeRating(Integer rating, Book book) {
        if(rating < 1 || rating > 5) {
            throw new RuntimeException("Wrong rating!");
        }
        book.setAvgRating((double)(book.getAvgRating() * book.getCountRating() - rating) / (book.getCountRating() - 1));
        bookRepository.save(book);
    }

    private BookDto mapToBookDto(Book book) {
        BookDto bookDto = BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .pages(book.getPages())
                .cover(book.getCover())
                .status(book.isStatus())
                .avgRating(book.getAvgRating())
                .publisher(book.getPublisher() != null ? book.getPublisher().getName() : null)
                .authors(book.getAuthors().stream().map(Author::getName).collect(Collectors.toSet()))
                .categories(book.getCategories().stream().map(Category::getName).collect(Collectors.toSet()))
                .build();
        return bookDto;
    }

}