package com.projekt.ems.Services;

import com.projekt.ems.Dto.UserBookDto;

public interface UserBookService {
    UserBookDto findUserBookById(Long id);
    UserBookDto getOrCreateUserBook(Long userId, Long bookId);
    UserBookDto saveUserBook(UserBookDto userBook);
    UserBookDto crateUserBook(Long userId, Long bookId, Integer status);
    UserBookDto updateUserBookStatus(Long id, int status);
}
