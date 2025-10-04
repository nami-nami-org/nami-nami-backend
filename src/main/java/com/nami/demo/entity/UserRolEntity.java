package com.nami.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario_roles", indexes = {
        @Index(name = "idx_usuario_roles_usuario", columnList = "id_usuario"),
        @Index(name = "idx_usuario_roles_rol", columnList = "id_rol"),
        @Index(name = "idx_usuario_roles_unique", columnList = "id_usuario,id_rol", unique = true)
})
public class UserRolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private RolEntity role;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public UserRolEntity() {
    }

    public UserRolEntity(Long id, UserEntity user, RolEntity role, boolean active, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.role = role;
        this.active = active;
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

    public RolEntity getRole() {
        return role;
    }

    public void setRole(RolEntity role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
