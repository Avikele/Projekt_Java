package com.projekt.ems.Services.impl;

import com.projekt.ems.Dto.UserDto;
import com.projekt.ems.Models.User;
import com.projekt.ems.Errors.EmailAlreadyExistsException;
import com.projekt.ems.Repositories.UserRepository;
import com.projekt.ems.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userToUserDto(user);

    }

    @Override
    public UserDto saveUser(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setSurName(userDto.getSurName());
        user.setEmail(userDto.getEmail());
        user.setStatus(1);
        user.setPrivilages(0);
        user.setCreationDate(LocalDate.now());

        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new EmailAlreadyExistsException("Email" + userDto.getEmail() +"already exists");
        }

        User savedUser = userRepository.save(user);
        return userToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setSurName(userDto.getSurName());
        user.setEmail(userDto.getEmail());
        user.setPrivilages(userDto.getPrivilages());
        user.setStatus(userDto.getStatus());
        User savedUser = userRepository.save(user);

        return userToUserDto(savedUser);

    }

    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }


    private UserDto userToUserDto(User user) {
        if (user == null) {
            throw new RuntimeException("User not found");
        }



        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .surName(user.getSurName())
                .creationDate(user.getCreationDate())
                .status(user.getStatus())
                .privilages(user.getPrivilages())
                .build();
    }

}
