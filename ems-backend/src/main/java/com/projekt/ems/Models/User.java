package com.projekt.ems.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "password", unique = true, nullable = false, length = 40)
    private String password;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "surName", nullable = false, length = 100)
    private String surName;

    @Column(name = "status", nullable = false, length = 5)
    private Integer status;

    @Column(name = "privilages", nullable = false, length = 5)
    private Integer privilages;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDate creationDate;

    @Column(name = "bookReadLastMonth", length = 11)
    private Integer bookReadLastMonth;

    @Column(name = "bookReadLastYear", length = 11)
    private Integer bookReadLastYear;

    @Column(name = "pagesRead", length = 11)
    private Integer pagesRead;

    @Column(name = "time")
    private LocalTime time;  // Nowe pole typu LocalTime, nullable

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserBook> userBooks = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Notification> notifications = new HashSet<>();
}
