package com.nami.demo.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "categorias_platillo")
public class DishCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    private String description;

    @Column(length = 50)
    private String type;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "category")
    private Set<DishCategoryLinkEntity> links;

    public DishCategoryEntity(Long id, String name, String description, String type, boolean active, LocalDateTime createdAt, Set<DishCategoryLinkEntity> links) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.active = active;
        this.createdAt = createdAt;
        this.links = links;
    }

    public DishCategoryEntity() {}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Set<DishCategoryLinkEntity> getLinks() {
        return links;
    }

    public void setLinks(Set<DishCategoryLinkEntity> links) {
        this.links = links;
    }
}
