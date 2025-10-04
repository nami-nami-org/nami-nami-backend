package com.nami.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "roles", indexes = {
        @Index(name = "idx_roles_nombre", columnList = "nombre", unique = true)
})
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false)
    private boolean activo = true;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public RolEntity() {
    }

    public RolEntity(Long id, String nombre, boolean activo, LocalDateTime createdAt) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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