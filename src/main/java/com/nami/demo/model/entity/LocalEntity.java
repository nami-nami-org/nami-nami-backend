package com.nami.demo.model.entity;

import com.nami.demo.model.enums.RestaurantStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "locals")
public class LocalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "local_name", nullable = false, length = 200)
    private String localName;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantEntity restaurant;

    @Column(name = "trade_name", nullable = false, length = 200)
    private String tradeName;

    private String description;

    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DishEntity> dishes = new HashSet<>();

    @Column(nullable = false, length = 400)
    private String address;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(name = "delivery_cost")
    private double deliveryCost = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RestaurantStatus status = RestaurantStatus.CERRADO;

    @Column(name = "average_rating")
    private Double averageRating ;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "delivery_available")
    private boolean deliveryAvailable = true;

    @Column(name = "average_delivery_time")
    private Integer averageDeliveryTime;

    @Column(name = "opening_time")
    private LocalDateTime openingTime;

    @Column(name = "closing_time")
    private LocalDateTime closingTime;

    private double altitude;

    private double longitude;

    private String direction;



    public LocalEntity() {
    }

    public LocalEntity(Long id, String localName, RestaurantEntity restaurant, String tradeName, String description,
                       Set<DishEntity> dishes, String address, String phone, double deliveryCost,
                       RestaurantStatus status, Double averageRating, LocalDateTime createdAt, LocalDateTime updatedAt,
                       boolean deliveryAvailable, Integer averageDeliveryTime, LocalDateTime openingTime,
                       LocalDateTime closingTime, double altitude, double longitude, String direction) {
        this.id = id;
        this.localName = localName;
        this.restaurant = restaurant;
        this.tradeName = tradeName;
        this.description = description;
        this.dishes = dishes;
        this.address = address;
        this.phone = phone;
        this.deliveryCost = deliveryCost;
        this.status = status;
        this.averageRating = averageRating;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deliveryAvailable = deliveryAvailable;
        this.averageDeliveryTime = averageDeliveryTime;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.altitude = altitude;
        this.longitude = longitude;
        this.direction = direction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<DishEntity> getDishes() {
        return dishes;
    }

    public void setDishes(Set<DishEntity> dishes) {
        this.dishes = dishes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public RestaurantStatus getStatus() {
        return status;
    }

    public void setStatus(RestaurantStatus status) {
        this.status = status;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
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

    public boolean isDeliveryAvailable() {
        return deliveryAvailable;
    }

    public void setDeliveryAvailable(boolean deliveryAvailable) {
        this.deliveryAvailable = deliveryAvailable;
    }

    public Integer getAverageDeliveryTime() {
        return averageDeliveryTime;
    }

    public void setAverageDeliveryTime(Integer averageDeliveryTime) {
        this.averageDeliveryTime = averageDeliveryTime;
    }

    public LocalDateTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalDateTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalDateTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalDateTime closingTime) {
        this.closingTime = closingTime;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
