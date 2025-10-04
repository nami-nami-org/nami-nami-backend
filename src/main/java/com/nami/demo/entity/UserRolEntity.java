package com.nami.demo.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class UserRolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private RolEntity rol;

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // getters y setters

    @Entity
    @Table(name = "usuarios", indexes = {
        @Index(name = "idx_usuarios_email", columnList = "email", unique = true),
        @Index(name = "idx_usuarios_activo", columnList = "activo")
    })
    public static class UserEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(nullable = false, length = 150)
        private String nombre;

        @Column(nullable = false, length = 255, unique = true)
        private String email;

        @Column(nullable = false, length = 255)
        private String contraseña;

        @Column(length = 20)
        private String telefono;

        @Column(nullable = false)
        private Boolean activo = true;

        @Column(name = "created_at", nullable = false)
        private LocalDateTime createdAt = LocalDateTime.now();

        @Column(name = "updated_at", nullable = false)
        private LocalDateTime updatedAt = LocalDateTime.now();

        @OneToMany(mappedBy = "usuario")
        private List<UsuarioRol> roles;

        @OneToOne(mappedBy = "propietario")
        private Restaurante restaurante;

    }
}
