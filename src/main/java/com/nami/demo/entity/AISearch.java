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
    private UserEntity user;

    private String userQuery;
    private String intent;

    @Column(columnDefinition = "TEXT")
    private String extractedParameters;

    @Column(columnDefinition = "TEXT")
    private String foundResults;

    private Integer totalResults;
    private Integer satisfaction;
    private Integer responseTimeMs;
    private String sessionId;
    private LocalDateTime createdAt;

    public AISearch() {}

    public AISearch(Long id, UserEntity user, String userQuery, String intent, String extractedParameters, String foundResults,
                    Integer totalResults, Integer satisfaction, Integer responseTimeMs, String sessionId, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.userQuery = userQuery;
        this.intent = intent;
        this.extractedParameters = extractedParameters;
        this.foundResults = foundResults;
        this.totalResults = totalResults;
        this.satisfaction = satisfaction;
        this.responseTimeMs = responseTimeMs;
        this.sessionId = sessionId;
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

    public String getUserQuery() {
        return userQuery;
    }

    public void setUserQuery(String userQuery) {
        this.userQuery = userQuery;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getExtractedParameters() {
        return extractedParameters;
    }

    public void setExtractedParameters(String extractedParameters) {
        this.extractedParameters = extractedParameters;
    }

    public String getFoundResults() {
        return foundResults;
    }

    public void setFoundResults(String foundResults) {
        this.foundResults = foundResults;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(Integer satisfaction) {
        this.satisfaction = satisfaction;
    }

    public Integer getResponseTimeMs() {
        return responseTimeMs;
    }

    public void setResponseTimeMs(Integer responseTimeMs) {
        this.responseTimeMs = responseTimeMs;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
