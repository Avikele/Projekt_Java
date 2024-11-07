package com.projekt.ems.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", unique = true, nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "surName", nullable = false)
    private String surName;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "privilages", nullable = false)
    private Integer privilages;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDate creationDate;

    @Column(name = "bookReadLastMonth")
    private Integer bookReadLastMonth;

    @Column(name = "bookReadLastYear")
    private Integer bookReadLastYear;

    @Column(name = "pagesRead")
    private Integer pagesRead;
}