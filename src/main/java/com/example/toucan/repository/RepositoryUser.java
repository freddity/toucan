package com.example.toucan.repository;

import com.example.toucan.model.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SuppressWarnings("ALL")
@Repository
public interface RepositoryUser extends JpaRepository<EntityUser, UUID> {

    EntityUser findByUsername(String username);
    EntityUser findByUuid(UUID uuid);
    EntityUser save(EntityUser entityUser);

    @Transactional
    void deleteByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE EntityUser u SET u.password = :password WHERE u.username = :username")
    void changePassword(@Param("username") String username,
                        @Param("password") String newHashedPassword);

    @Transactional
    @Modifying
    @Query("UPDATE EntityUser u SET u.lockStatus = :status WHERE u.username = :username")
    void setLockStatus(@Param("username") String username,
                       @Param("status") boolean status);
}
