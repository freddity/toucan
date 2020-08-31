package com.example.toucan.repository;

import com.example.toucan.model.entity.EntityUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositoryUser extends CrudRepository<EntityUser, UUID> {

    EntityUser findEntityUserByUsername(String username);
    EntityUser save(EntityUser entityUser);
}
