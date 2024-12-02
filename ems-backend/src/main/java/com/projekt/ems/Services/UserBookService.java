package com.projekt.ems.Services;

import com.projekt.ems.Dto.UserBookDto;

public interface UserBookService {
    UserBookDto findUserBookById(Long id);
    UserBookDto saveUserBook(UserBookDto userBook);
    UserBookDto updateUserBookStatus(Long id, int status);
}
