package com.nami.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "usuarios", indexes = {
        @Index(name = "idx_usuarios_email", columnList = "email", unique = true),
        @Index(name = "idx_usuarios_activo", columnList = "activo")
})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String contraseña;

    @Column(length = 20)
    private String telefono;

    @Column(nullable = false)
    private boolean activo = true;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "usuario")
    private Set<UserRolEntity> roles;

    @OneToMany(mappedBy = "usuario")
    private Set<OrderEntity> orderEntities;

    @OneToMany(mappedBy = "usuario")
    private Set<ReviewEntity> valoraciones;

    public UserEntity() {
    }

    public UserEntity(Long id, String nombre, String email, String contraseña, String telefono, boolean activo, LocalDateTime createdAt,
                      LocalDateTime updatedAt, Set<UserRolEntity> roles, Set<OrderEntity> orderEntities, Set<ReviewEntity> valoraciones) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.activo = activo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.roles = roles;
        this.orderEntities = orderEntities;
        this.valoraciones = valoraciones;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<UserRolEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRolEntity> roles) {
        this.roles = roles;
    }

    public Set<OrderEntity> getOrderEntities() {
        return orderEntities;
    }

    public void setOrderEntities(Set<OrderEntity> orderEntities) {
        this.orderEntities = orderEntities;
    }

    public Set<ReviewEntity> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(Set<ReviewEntity> valoraciones) {
        this.valoraciones = valoraciones;
    }
}
