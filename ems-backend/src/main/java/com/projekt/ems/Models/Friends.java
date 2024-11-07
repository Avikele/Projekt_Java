package com.projekt.ems.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "friends")
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;  // Identyfikator relacji (np. dla zapytania)

    @ManyToOne
    @JoinColumn(name = "user_id1", nullable = false)
    private User user1;  // Pierwszy użytkownik w relacji

    @ManyToOne
    @JoinColumn(name = "user_id2", nullable = false)
    private User user2;  // Drugi użytkownik w relacji

    @Column(name = "status", nullable = false, length = 11)
    private Integer status;  // Status relacji (0 - oczekiwanie, 1 - zaakceptowane, 2 - odrzucone)
}
