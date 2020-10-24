package com.example.toucan.repository;

import com.example.toucan.model.entity.EntityNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositoryNote extends JpaRepository<EntityNote, UUID> {

    EntityNote findByUuid(UUID uuid);
}
