package com.nami.demo.user.repository;


import com.nami.demo.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RolEntity, Long> {
    Optional<RolEntity> findByName(String name);
}
