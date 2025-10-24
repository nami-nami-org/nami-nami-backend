package com.nami.demo.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reseñas_local")
public class LocalReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_local", nullable = false)
    private LocalEntity local;

    private String comment;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public LocalReviewEntity(Long id, UserEntity user, LocalEntity local, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.local = local;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public LocalReviewEntity() {

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