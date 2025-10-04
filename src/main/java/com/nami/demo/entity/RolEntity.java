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
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    private String nombre;

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "rol")
    private List<UsuarioRol> usuarios;
}