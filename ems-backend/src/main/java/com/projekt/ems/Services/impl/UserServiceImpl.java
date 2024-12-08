package com.projekt.ems.Services.impl;

import com.projekt.ems.Dto.UserDto;
import com.projekt.ems.Models.User;
import com.projekt.ems.Errors.EmailAlreadyExistsException;
import com.projekt.ems.Repositories.UserRepository;
import com.projekt.ems.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();

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
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
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
        User savedUser = userRepository.save(user);

        return userToUserDto(savedUser);

    }

    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public void addTime(LocalTime time, User user) {
        user.setTime(user.getTime().plusHours(time.getHour())
                .plusMinutes(time.getMinute())
                .plusSeconds(time.getSecond()));
        userRepository.save(user);
    }

    @Override
    public void removeTime(LocalTime time, User user) {
        user.setTime(user.getTime().minusHours(time.getHour())
                .minusMinutes(time.getMinute())
                .minusSeconds(time.getSecond()));
    }

    @Override
    public void addReadBook(User user) {
        user.setBookRead(user.getBookRead() + 1);
        userRepository.save(user);
    }

    @Override
    public void removeReadBook(User user) {
        user.setBookRead(user.getBookRead() - 1);
        userRepository.save(user);
    }

    @Override
    public void addPagesRead(Integer pages, User user) {
        user.setPagesRead(user.getPagesRead() + pages);
        userRepository.save(user);
    }



    public void removePagesRead(Integer pages, User user) {
        user.setPagesRead(user.getPagesRead() - pages);
        userRepository.save(user);
    }


    private UserDto userToUserDto(User user) {
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return new UserDto(user);
    }


}
