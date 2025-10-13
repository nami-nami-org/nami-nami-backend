package com.nami.demo.model.entity;

import com.nami.demo.model.enums.RestaurantStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "restaurants")
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private UserEntity user;

    @Column(name = "commercial_name", nullable = false, length = 200)
    private String commercialName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RestaurantStatus status = RestaurantStatus.CERRADO;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LocalEntity> locals = new HashSet<>();

    @Column(name = "average_rating")
    private BigDecimal averageRating;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RestaurantCategoryLinkEntity> categoryLinks = new HashSet<>();

    @Column(length = 20)
    private String ruc;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "business_name", length = 255)
    private String businessName;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "website_url")
    private String websiteUrl;

    // -------------------
    // Constructors
    // -------------------

    public RestaurantEntity() {
    }

    public RestaurantEntity(Long id, UserEntity user, String commercialName, String description, String phone,
                            RestaurantStatus status, Set<LocalEntity> locals, BigDecimal averageRating,
                            Set<RestaurantCategoryLinkEntity> categoryLinks, String ruc, String logoUrl,
                            String imageUrl, String businessName, LocalDateTime createdAt,
                            LocalDateTime updatedAt, String websiteUrl) {
        this.id = id;
        this.user = user;
        this.commercialName = commercialName;
        this.description = description;
        this.phone = phone;
        this.status = status;
        this.locals = locals;
        this.averageRating = averageRating;
        this.categoryLinks = categoryLinks;
        this.ruc = ruc;
        this.logoUrl = logoUrl;
        this.imageUrl = imageUrl;
        this.businessName = businessName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.websiteUrl = websiteUrl;
    }

    // -------------------
    // Getters and Setters
    // -------------------

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

    public String getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RestaurantStatus getStatus() {
        return status;
    }

    public void setStatus(RestaurantStatus status) {
        this.status = status;
    }

    public Set<LocalEntity> getLocals() {
        return locals;
    }

    public void setLocals(Set<LocalEntity> locals) {
        this.locals = locals;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }

    public Set<RestaurantCategoryLinkEntity> getCategoryLinks() {
        return categoryLinks;
    }

    public void setCategoryLinks(Set<RestaurantCategoryLinkEntity> categoryLinks) {
        this.categoryLinks = categoryLinks;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
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

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }
}
