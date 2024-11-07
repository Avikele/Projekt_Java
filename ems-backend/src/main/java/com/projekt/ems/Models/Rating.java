package com.projekt.ems.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "rating", nullable = false)
    private Integer rating;  // The rating value

    @OneToOne
    @JoinColumn(name = "user_book_id", nullable = false)
    private UserBook userBook;  // Relationship with UserBook
}
