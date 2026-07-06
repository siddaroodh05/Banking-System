package com.siddu.auth.repository;

import com.siddu.auth.Enums.RoleName;
import com.siddu.auth.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByName(RoleName name);

    boolean existsByName(RoleName name);
    @Query("select r.id from RoleEntity r where r.name = :name")
    Optional<Long> findIdByName(@Param("name") RoleName name);


}
