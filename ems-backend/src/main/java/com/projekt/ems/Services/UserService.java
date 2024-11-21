package com.projekt.ems.Services;

import com.projekt.ems.Dto.UserDto;
import com.projekt.ems.Models.User;

import java.time.LocalTime;
import java.util.List;

public interface UserService {
    List<UserDto> findAllUsers();
    UserDto findUserById(Long id);
    UserDto saveUser(UserDto user);
    UserDto updateUser(Long id, UserDto user);
    void deleteUser(Long id);
    void addTime(LocalTime time, User user);
    void removeTime(LocalTime time, User user);
    void addReadBook(User user);
    void removeReadBook(User user);
    void addPagesRead(Integer pages, User user);
    void removePagesRead(Integer pages, User user);
}
