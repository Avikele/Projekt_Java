package com.projekt.ems.Services.impl;

import com.projekt.ems.Dto.UserBookDto;
import com.projekt.ems.Models.Book;
import com.projekt.ems.Models.User;
import com.projekt.ems.Models.UserBook;
import com.projekt.ems.Repositories.BookRepository;
import com.projekt.ems.Repositories.UserBookRepository;
import com.projekt.ems.Repositories.UserRepository;
import com.projekt.ems.Services.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBookServiceImpl implements UserBookService {

    private UserBookRepository userBookRepository;
    private BookRepository bookRepository;
    private UserRepository userRepository;
    @Autowired
    public UserBookServiceImpl(UserBookRepository userBookRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.userBookRepository = userBookRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserBookDto findUserBookById(Long id) {
        UserBook userBook = userBookRepository.findById(id).orElse(null);
        return mapToUserBookDto(userBook);
    }

    public UserBookDto getOrCreateUserBook(Long userId, Long bookId) {
        UserBook result = userBookRepository.getUserBook(userId, bookId);
        if (result != null) {
            return mapToUserBookDto(result);
        }
        return saveUserBook(new UserBookDto(0L, userId, bookId, 0));
    }

    public UserBookDto saveUserBook(UserBookDto userBookDto) {
        UserBook userBook = new UserBook();
        Book book = bookRepository.findById(userBookDto.getBook_id()).orElseThrow(() -> new RuntimeException("Book not found"));
        User user = userRepository.findById(userBookDto.getUser_id()).orElseThrow(() -> new RuntimeException("User not found"));
        userBook.setBook(book);
        userBook.setUser(user);
        userBook.setStatus(userBookDto.getStatus());

        UserBook saveUserBook = userBookRepository.save(userBook);
        return mapToUserBookDto(saveUserBook);
    }

    @Override
    public UserBookDto crateUserBook(Long userId, Long bookId, Integer status) {
        UserBook userBook = new UserBook();
        Book book = bookRepository.findById(userId).orElseThrow(() -> new RuntimeException("Book not found"));
        User user = userRepository.findById(bookId).orElseThrow(() -> new RuntimeException("User not found"));
        userBook.setBook(book);
        userBook.setUser(user);
        userBook.setStatus(status);

        UserBook saveUserBook = userBookRepository.save(userBook);
        return mapToUserBookDto(saveUserBook);
    }

    public UserBookDto updateUserBookStatus(Long id, int status) {
        UserBook userBook = userBookRepository.findById(id).orElseThrow(() -> new RuntimeException("UserBook not found"));
        userBook.setStatus(status);
        UserBook saveUserBook = userBookRepository.save(userBook);
        return mapToUserBookDto(saveUserBook);
    }

    private UserBookDto mapToUserBookDto(UserBook userBook) {
        if( userBook == null ) {
            return null;
        }

        return UserBookDto.builder()
                .id(userBook.getId())
                .book_id(userBook.getBook().getId())
                .user_id(userBook.getUser().getId())
                .status(userBook.getStatus())
                .build();
    }
}
