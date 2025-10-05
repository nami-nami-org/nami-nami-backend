package com.nami.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "valoraciones")
public class RestaurantRatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UserEntity user;

    @ManyToOne @JoinColumn(name = "id_restaurante", nullable = false)
    private RestaurantEntity restaurant;

    @Column(nullable = false)
    private int rating;

    private String comment;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public RestaurantRatingEntity() {
    }

    public RestaurantRatingEntity(Long id, UserEntity user, RestaurantEntity restaurant, int rating,
                                  String comment, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.restaurant = restaurant;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}