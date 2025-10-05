package com.nami.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "platillo_categorias")
public class DishCategoryLinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "id_platillo", nullable = false)
    private DishEntity dish;

    @ManyToOne @JoinColumn(name = "id_categoria", nullable = false)
    private DishCategoryEntity category;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public DishCategoryLinkEntity() {
    }

    public DishCategoryLinkEntity(Long id, DishEntity dish, DishCategoryEntity category, LocalDateTime createdAt) {
        this.id = id;
        this.dish = dish;
        this.category = category;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DishEntity getDish() {
        return dish;
    }

    public void setDish(DishEntity dish) {
        this.dish = dish;
    }

    public DishCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(DishCategoryEntity category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}