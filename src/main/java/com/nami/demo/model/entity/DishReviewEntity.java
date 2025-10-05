package com.nami.demo.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reseñas_platillo")
public class DishReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "id_usuario", nullable = false)
    private UserEntity user;

    @ManyToOne @JoinColumn(name = "id_platillo", nullable = false)
    private DishEntity dish;

    private String comment;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}