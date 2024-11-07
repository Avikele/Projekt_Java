package com.projekt.ems.Controllers;


import com.projekt.ems.Models.User;
import com.projekt.ems.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers() {return userService.getAllUsers();}

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) {return userService.createUser(user);}

    @PostMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = userService.getUserById(id);

        // Sprawdzamy, czy użytkownik o podanym ID istnieje
        if(userOptional.isPresent()) {
            User existingUser = userOptional.get();

            // Zaktualizuj tylko te pola, które są dostępne w ciele żądania
            if (user.getUsername() != null) {
                existingUser.setUsername(user.getUsername());
            }
            if (user.getPassword() != null) {
                existingUser.setPassword(user.getPassword());
            }
            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getFirstName() != null) {
                existingUser.setFirstName(user.getFirstName());
            }
            if (user.getSurName() != null) {
                existingUser.setSurName(user.getSurName());
            }
            if (user.getStatus() != null) {
                existingUser.setStatus(user.getStatus());
            }
            if (user.getPrivilages() != null) {
                existingUser.setPrivilages(user.getPrivilages());
            }
            if (user.getCreationDate() != null) {
                existingUser.setCreationDate(user.getCreationDate());
            }

            // Zapisz zaktualizowanego użytkownika
            User updatedUser = userService.updateUser(existingUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            // Jeśli użytkownik o podanym ID nie istnieje
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.getUserById(id);
        if(userOptional.isPresent()) {
            userService.DeleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
