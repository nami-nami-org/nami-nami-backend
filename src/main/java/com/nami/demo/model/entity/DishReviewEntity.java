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

    public DishReviewEntity() {
    }

    public DishReviewEntity(Long id, UserEntity user, DishEntity dish, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.dish = dish;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public DishEntity getDish() {
        return dish;
    }

    public void setDish(DishEntity dish) {
        this.dish = dish;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}