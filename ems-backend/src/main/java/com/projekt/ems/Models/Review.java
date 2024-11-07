package com.projekt.ems.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "text", nullable = false, length = 500)
    private String text;

    @Column(name = "status", nullable = false, length = 5)
    private Integer status;

    @OneToOne
    @JoinColumn(name = "user_book_id", nullable = false)
    private UserBook userBook;
}
