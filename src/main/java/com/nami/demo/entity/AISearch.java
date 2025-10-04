package com.nami.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "busquedas_ia")
public class AISearch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UserEntity usuario;

    private String queryUsuario;
    private String intent;
    @Column(columnDefinition = "TEXT")
    private String parametrosExtraidos;
    @Column(columnDefinition = "TEXT")
    private String resultadosEncontrados;
    private Integer totalResultados;
    private Integer satisfaccion;
    private Integer tiempoRespuestaMs;
    private String sesionId;
    private LocalDateTime createdAt;


    public AISearch() {}

    public AISearch(Long id, UserEntity usuario, String queryUsuario, String intent, String parametrosExtraidos, String resultadosEncontrados,
                    Integer totalResultados, Integer satisfaccion, Integer tiempoRespuestaMs, String sesionId, LocalDateTime createdAt) {
        this.id = id;
        this.usuario = usuario;
        this.queryUsuario = queryUsuario;
        this.intent = intent;
        this.parametrosExtraidos = parametrosExtraidos;
        this.resultadosEncontrados = resultadosEncontrados;
        this.totalResultados = totalResultados;
        this.satisfaccion = satisfaccion;
        this.tiempoRespuestaMs = tiempoRespuestaMs;
        this.sesionId = sesionId;
        this.createdAt = createdAt;
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

    public String getQueryUsuario() {
        return queryUsuario;
    }

    public void setQueryUsuario(String queryUsuario) {
        this.queryUsuario = queryUsuario;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getParametrosExtraidos() {
        return parametrosExtraidos;
    }

    public void setParametrosExtraidos(String parametrosExtraidos) {
        this.parametrosExtraidos = parametrosExtraidos;
    }

    public String getResultadosEncontrados() {
        return resultadosEncontrados;
    }

    public void setResultadosEncontrados(String resultadosEncontrados) {
        this.resultadosEncontrados = resultadosEncontrados;
    }

    public Integer getTotalResultados() {
        return totalResultados;
    }

    public void setTotalResultados(Integer totalResultados) {
        this.totalResultados = totalResultados;
    }

    public Integer getSatisfaccion() {
        return satisfaccion;
    }

    public void setSatisfaccion(Integer satisfaccion) {
        this.satisfaccion = satisfaccion;
    }

    public Integer getTiempoRespuestaMs() {
        return tiempoRespuestaMs;
    }

    public void setTiempoRespuestaMs(Integer tiempoRespuestaMs) {
        this.tiempoRespuestaMs = tiempoRespuestaMs;
    }

    public String getSesionId() {
        return sesionId;
    }

    public void setSesionId(String sesionId) {
        this.sesionId = sesionId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
