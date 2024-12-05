package com.projekt.ems.Repositories;

import com.projekt.ems.Dto.LoginRequest;
import com.projekt.ems.Dto.UserDto;
import com.projekt.ems.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    Optional<LoginRequest> findByUsername(String username);

}
