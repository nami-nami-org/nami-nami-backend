package com.nami.demo.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

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
    private UserEntity usuario;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private RolEntity rol;

    @Column(nullable = false)
    private boolean activo = true;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public UserRolEntity() {
    }

    public UserRolEntity(Long id, UserEntity usuario, RolEntity rol, boolean activo, LocalDateTime createdAt) {
        this.id = id;
        this.usuario = usuario;
        this.rol = rol;
        this.activo = activo;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

    public RolEntity getRol() {
        return rol;
    }

    public void setRol(RolEntity rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
