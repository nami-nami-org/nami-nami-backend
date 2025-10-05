package com.nami.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "busquedas_ia")
public class SearchAIEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UserEntity user;

    @Column(nullable = false)
    private String userQuery;

    private String intent;

    private String extractedParams;

    private String foundResults;

    private int totalResults = 0;

    private int satisfaction;

    private int responseTimeMs;

    private String sessionId;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public SearchAIEntity() {
    }

    public SearchAIEntity(Long id, UserEntity user, String userQuery, String intent, String extractedParams,
                          String foundResults, int totalResults, int satisfaction, int responseTimeMs, String sessionId,
                          LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.userQuery = userQuery;
        this.intent = intent;
        this.extractedParams = extractedParams;
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

    public String getExtractedParams() {
        return extractedParams;
    }

    public void setExtractedParams(String extractedParams) {
        this.extractedParams = extractedParams;
    }

    public String getFoundResults() {
        return foundResults;
    }

    public void setFoundResults(String foundResults) {
        this.foundResults = foundResults;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }

    public int getResponseTimeMs() {
        return responseTimeMs;
    }

    public void setResponseTimeMs(int responseTimeMs) {
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
