package com.example.toucan.repository;

import com.example.toucan.model.entity.EntityNote;
import com.example.toucan.model.entity.EntityUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositoryNote extends CrudRepository<EntityNote, UUID> {

    EntityNote findEntityNoteByOwner(EntityUser entityUser);
}
