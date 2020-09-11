package com.example.toucan.repository;

import com.example.toucan.model.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface RepositoryUser extends JpaRepository<EntityUser, UUID> {

    EntityUser findByUsername(String username);
    EntityUser save(EntityUser entityUser);
}
