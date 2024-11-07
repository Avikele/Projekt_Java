package com.projekt.ems.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "message", nullable = false, length = 100)
    private String message;


    @Column(name = "status", nullable = false, length = 5)
    private String status;

    @Column(name = "type", nullable = false, length = 5)
    private Integer type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime dateCreated;
}
