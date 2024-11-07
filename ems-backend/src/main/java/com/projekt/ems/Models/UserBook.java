package com.projekt.ems.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_has_book")
public class UserBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "status", length = 5)
    private Integer status;

    @OneToOne(mappedBy = "userBook", cascade = CascadeType.ALL, orphanRemoval = true)
    private Rating rating;

    @OneToOne(mappedBy = "userBook", cascade = CascadeType.ALL, orphanRemoval = true)
    private Review review;
}
