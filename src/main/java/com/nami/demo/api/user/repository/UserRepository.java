package com.nami.demo.routes.user.repository;
import com.nami.demo.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);

    // @Query("SELECT r FROM r JOIN r.roles ur WHERE ur.user.id = :userId")
    // List<Role> findRolesByUserId(@Param("userIs") Long userId)
}
