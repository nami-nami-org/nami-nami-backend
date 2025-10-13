package com.nami.demo.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dishes")
public class DishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "local_id", nullable = false)
    private LocalEntity local;

    @Column(nullable = false, length = 200)
    private String name;

    private String description;

    private double discount = 1;

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

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "dish_images",
            joinColumns = @JoinColumn(name = "dish_id")
    )
    @Column(name = "image_url", nullable = false)
    private Set<String> imageUrls = new HashSet<>();

    @Column(nullable = false, length = 200)
    @Comment("Average preparation time in minutes")
    private Integer prepTime;

    @OneToMany(mappedBy = "dish")
    private Set<DishRatingEntity> ratings;

    private Double avgRating = null;

    public DishEntity() {
    }

    public DishEntity(Long id, LocalEntity local, String name, String description, double discount,
                      double price, String imageUrl, boolean available, LocalDateTime createdAt,
                      Set<DishCategoryLinkEntity> categories, Set<DishReviewEntity> reviews,
                      Set<String> imageUrls, Integer prepTime, Set<DishRatingEntity> ratings, Double avgRating) {
        this.id = id;
        this.local = local;
        this.name = name;
        this.description = description;
        this.discount = discount;
        this.price = price;
        this.imageUrl = imageUrl;
        this.available = available;
        this.createdAt = createdAt;
        this.categories = categories;
        this.reviews = reviews;
        this.imageUrls = imageUrls;
        this.prepTime = prepTime;
        this.ratings = ratings;
        this.avgRating = avgRating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalEntity getLocal() {
        return local;
    }

    public void setLocal(LocalEntity local) {
        this.local = local;
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<DishCategoryLinkEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<DishCategoryLinkEntity> categories) {
        this.categories = categories;
    }

    public Set<DishReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(Set<DishReviewEntity> reviews) {
        this.reviews = reviews;
    }

    public Set<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(Set<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Set<DishRatingEntity> getRatings() {
        return ratings;
    }

    public void setRatings(Set<DishRatingEntity> ratings) {
        this.ratings = ratings;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }
}
