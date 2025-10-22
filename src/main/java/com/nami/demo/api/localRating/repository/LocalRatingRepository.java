package com.nami.demo.api.localRating.repository;

import com.nami.demo.model.entity.LocalRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalRatingRepository extends JpaRepository<LocalRatingEntity, Long> {
    List<LocalRatingEntity> findByLocalId(Long localId);
}
