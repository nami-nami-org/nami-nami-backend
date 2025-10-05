package com.nami.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "categorias_restaurante")
public class RestaurantCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    private String description;

    @Column(nullable = false)
    private boolean active = true;

    @Column(name = "orden")
    private int orderIndex = 0;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RestaurantCategoryLinkEntity> restaurantLinks;

    public RestaurantCategoryEntity() {
    }

    public RestaurantCategoryEntity(Long id, String name, String description,
                                    boolean active, int orderIndex, LocalDateTime createdAt, Set<RestaurantCategoryLinkEntity> restaurantLinks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.orderIndex = orderIndex;
        this.createdAt = createdAt;
        this.restaurantLinks = restaurantLinks;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<RestaurantCategoryLinkEntity> getRestaurantLinks() {
        return restaurantLinks;
    }

    public void setRestaurantLinks(Set<RestaurantCategoryLinkEntity> restaurantLinks) {
        this.restaurantLinks = restaurantLinks;
    }
}