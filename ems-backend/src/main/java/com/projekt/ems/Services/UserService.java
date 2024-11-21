package com.projekt.ems.Services;

import com.projekt.ems.Dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAllUsers();
    UserDto findUserById(Long id);
    UserDto saveUser(UserDto user);
    UserDto updateUser(Long id, UserDto user);
    void deleteUser(Long id);
}
