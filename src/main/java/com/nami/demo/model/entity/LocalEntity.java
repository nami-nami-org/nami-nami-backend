package com.nami.demo.model.entity;

import com.nami.demo.model.enums.RestaurantStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "locales")
public class LocalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private RestaurantEntity restaurant;

    @Column(name = "nombre_comercial", nullable = false, length = 200)
    private String tradeName;

    private String description;

    @Column(nullable = false, length = 400)
    private String address;

    @Column(nullable = false, length = 20)
    private String phone;

    private double deliveryCost = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RestaurantStatus status = RestaurantStatus.CERRADO;

    private double averageRating = 0.0;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
