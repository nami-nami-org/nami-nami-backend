package com.nami.demo.entity;


import com.nami.demo.enums.RestaurantStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RestaurantEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_usuario", unique = true, nullable = false)
    private UserEntity usuario;

    @Column(nullable = false, length = 200)
    private String nombreComercial;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false, length = 400)
    private String direccion;

    @Column(nullable = false, length = 20)
    private String telefono;

    private BigDecimal costoEnvio;
    private Integer tiempoEntrega = 30;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RestaurantStatus estado = RestaurantStatus.CERRADO;

    private BigDecimal calificacionPromedio;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public RestaurantEntity() {
    }

    public RestaurantEntity(Long id, UserEntity usuario, String nombreComercial, String descripcion, String direccion, String telefono, BigDecimal costoEnvio,
                            Integer tiempoEntrega, RestaurantStatus estado, BigDecimal calificacionPromedio, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.usuario = usuario;
        this.nombreComercial = nombreComercial;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.costoEnvio = costoEnvio;
        this.tiempoEntrega = tiempoEntrega;
        this.estado = estado;
        this.calificacionPromedio = calificacionPromedio;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public BigDecimal getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(BigDecimal costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public Integer getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(Integer tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public RestaurantStatus getEstado() {
        return estado;
    }

    public void setEstado(RestaurantStatus estado) {
        this.estado = estado;
    }

    public BigDecimal getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public void setCalificacionPromedio(BigDecimal calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
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
}
