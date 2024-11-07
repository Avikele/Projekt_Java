package com.projekt.ems.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_statistics")
public class UserStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;  // Dodajemy id, ponieważ jest to często wymagane w każdej tabeli encji

    @Column(name = "pagesRead", nullable = false, length = 11)
    private int pagesRead;  // Liczba przeczytanych stron

    @Column(name = "time")
    private LocalTime time;  // Czas spędzony na czytaniu

    @Column(name = "ReadDate")
    private LocalDate readDate;  // Data, kiedy książka została przeczytana

    @ManyToOne
    @JoinColumn(name = "user_book_id", nullable = false)
    private UserBook userBook;  // Klucz obcy wskazujący na UserBook (relacja ManyToOne)
}
