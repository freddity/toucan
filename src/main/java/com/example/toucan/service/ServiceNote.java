package com.example.toucan.service;

import com.example.toucan.model.entity.EntityNote;
import com.example.toucan.model.entity.EntityUser;
import com.example.toucan.repository.RepositoryNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ServiceNote {

    private final RepositoryNote repositoryNote;

    @Autowired
    public ServiceNote(RepositoryNote repositoryNote) {
        this.repositoryNote = repositoryNote;
    }

    public EntityNote createNote(String title, String content, String creationDate, EntityUser owner){
        return repositoryNote.save(new EntityNote(title, content, creationDate, owner));
    }

    public void deleteNoteByUuid(UUID uuid){
        repositoryNote.deleteByUuid(uuid);
    }

}
