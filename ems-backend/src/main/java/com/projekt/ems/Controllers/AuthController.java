package com.projekt.ems.Controllers;

import com.projekt.ems.Dto.LoginRequest;
import com.projekt.ems.Dto.UserDto;
import com.projekt.ems.Models.User;
import com.projekt.ems.Repositories.UserRepository;
import com.projekt.ems.Services.UserService;
import com.projekt.ems.Services.impl.CustomUserDetailsServiceImpl;
import com.projekt.ems.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsServiceImpl userDetailsService;
    public final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, CustomUserDetailsServiceImpl userDetailsService, UserService userService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );


        String username = authentication.getName();
        LoginRequest userLogged = userRepository.findByUsername(username).orElse(null);

        String token = jwtUtil.generateToken(userLogged);

        return ResponseEntity.ok("Bearer " + token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {

        UserDto user = userService.saveUser(userDto);

        return ResponseEntity.ok("User registered successfully");
    }
}
