package com.projekt.ems.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projekt.ems.Dto.UserDto;
import com.projekt.ems.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestAttribute("privilages") int privilages) {
        if(privilages==1) {
            List<UserDto> users = userService.findAllUsers();
            return ResponseEntity.ok(users);
        }else {
            return ResponseEntity.status(403).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id, @RequestAttribute("privilages")int privilages,@RequestAttribute("Id") long Id) {

        if(privilages==0 && Id == id) {
            UserDto user = userService.findUserById(id);
            return ResponseEntity.ok(user);
        } else if (privilages == 1) {
            UserDto user = userService.findUserById(id);
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.status(403).body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto user = userService.saveUser(userDto);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto user = userService.updateUser(id, userDto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
