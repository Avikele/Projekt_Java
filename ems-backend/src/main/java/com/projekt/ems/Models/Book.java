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
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @Column(name = "pages", nullable = false, length = 11)
    private Integer pages;

    @Column(name = "cover", length = 45)
    private String cover;

    @Column(name = "status", nullable = false, length = 11)
    private boolean status;

    @Column(name = "avgRating", nullable = false, length = 11)
    private Double avgRating;

    @ManyToMany
    @JoinTable(
            name = "book_has_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserBook> userBooks = new HashSet<>();  // Relationship with UserBook
}
