package com.projekt.ems.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "surname", nullable = false, length = 100)
    private String surname;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();
}
