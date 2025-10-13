package com.nami.demo.api.local.repository;

import com.nami.demo.model.entity.LocalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LocalRepository extends JpaRepository<LocalEntity,Long > {

    @Query("SELECT l FROM LocalEntity l WHERE l.id = :localId AND l.restaurant.user.id = :userId")
    Optional<LocalEntity> findByIdAndUserId(Long localId, Long userId);

}
