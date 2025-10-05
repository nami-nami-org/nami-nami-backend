package com.nami.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "roles", indexes = {
        @Index(name = "idx_roles_nombre", columnList = "name", unique = true)
})
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "role")
    private Set<UserRolEntity> userRoles;

    public RolEntity() {
    }

    public RolEntity(Set<UserRolEntity> userRoles, LocalDateTime createdAt, boolean active, String name, Long id) {
        this.userRoles = userRoles;
        this.createdAt = createdAt;
        this.active = active;
        this.name = name;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<UserRolEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRolEntity> userRoles) {
        this.userRoles = userRoles;
    }
}
