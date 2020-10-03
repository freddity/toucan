package com.example.toucan.repository;

import com.example.toucan.model.entity.EntityPasswordReset;
import com.example.toucan.model.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositoryPasswordReset extends JpaRepository<EntityPasswordReset, UUID> {

    EntityPasswordReset findEntityPasswordResetByOwner(EntityUser owner);
}
