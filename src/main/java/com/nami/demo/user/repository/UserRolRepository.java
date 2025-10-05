package com.nami.demo.user.repository;

import com.nami.demo.entity.UserRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolRepository extends JpaRepository<UserRolEntity, Long> {
}
