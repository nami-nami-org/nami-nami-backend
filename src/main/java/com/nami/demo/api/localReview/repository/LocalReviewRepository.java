package com.nami.demo.api.localReview.repository;

import com.nami.demo.model.entity.LocalReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalReviewRepository extends JpaRepository<LocalReviewEntity, Long> {
    List<LocalReviewEntity> findByLocalId(Long localId);
}