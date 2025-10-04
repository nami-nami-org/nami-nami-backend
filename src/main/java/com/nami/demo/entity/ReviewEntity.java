package com.nami.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "valoraciones")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_pedido", unique = true, nullable = false)
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UserEntity usuario;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private RestaurantEntity restaurante;

    private Integer calificacion;
    private String comentario;
    private String respuestaRestaurante;
    private LocalDateTime fechaRespuesta;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ReviewEntity() {
    }

    public ReviewEntity(Long id, OrderEntity orderEntity, UserEntity usuario, RestaurantEntity restaurante, Integer calificacion, String comentario,
                        String respuestaRestaurante, LocalDateTime fechaRespuesta, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.orderEntity = orderEntity;
        this.usuario = usuario;
        this.restaurante = restaurante;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.respuestaRestaurante = respuestaRestaurante;
        this.fechaRespuesta = fechaRespuesta;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

    public RestaurantEntity getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestaurantEntity restaurante) {
        this.restaurante = restaurante;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getRespuestaRestaurante() {
        return respuestaRestaurante;
    }

    public void setRespuestaRestaurante(String respuestaRestaurante) {
        this.respuestaRestaurante = respuestaRestaurante;
    }

    public LocalDateTime getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(LocalDateTime fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
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