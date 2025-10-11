package com.nami.demo.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "valoraciones_local")
public class LocalRatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "id_usuario", nullable = false)
    private UserEntity user;

    @ManyToOne @JoinColumn(name = "id_local", nullable = false)
    private LocalEntity local;

    @Column(nullable = false)
    private int rating;

    private String comment;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
