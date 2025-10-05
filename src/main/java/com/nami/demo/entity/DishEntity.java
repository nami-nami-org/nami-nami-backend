package com.nami.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "platillos")
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "id_restaurante", nullable = false)
    private RestaurantEntity restaurant;

    @Column(nullable = false, length = 200)
    private String name;

    private String description;

    @Column(nullable = false)
    private double price;

    @Column(length = 500)
    private String imageUrl;

    @Column(nullable = false)
    private boolean available = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "dish")
    private Set<DishCategoryLinkEntity> categories;

    @OneToMany(mappedBy = "dish")
    private Set<DishReviewEntity> reviews;

    @OneToMany(mappedBy = "dish")
    private Set<DishRatingEntity> ratings;

    public DishEntity() {
    }


}
