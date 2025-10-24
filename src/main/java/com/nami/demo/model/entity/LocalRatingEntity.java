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

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public LocalRatingEntity(Long id, UserEntity user, LocalEntity local, int rating, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.local = local;
        this.rating = rating;
        this.createdAt = createdAt;
    }

    public LocalRatingEntity() {

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

    public LocalEntity getLocal() {
        return local;
    }

    public void setLocal(LocalEntity local) {
        this.local = local;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
