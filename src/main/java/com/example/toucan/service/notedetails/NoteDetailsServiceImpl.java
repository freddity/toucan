package com.example.toucan.service.notedetails;

import com.example.toucan.exception.NoteNotFoundException;
import com.example.toucan.model.entity.EntityNote;
import com.example.toucan.repository.RepositoryNote;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NoteDetailsServiceImpl implements NoteDetailsService {

    private final RepositoryNote repositoryNote;

    public NoteDetailsServiceImpl(RepositoryNote repositoryNote) {
        this.repositoryNote = repositoryNote;
    }

    @Override
    public NoteDetails loadNoteByUsername(UUID uuid) throws NoteNotFoundException {
        EntityNote entityNote = repositoryNote.findByUuid(uuid);
        if (entityNote == null) throw new NullPointerException();

        return new NoteDetailsImpl(
                entityNote.getUuid(),
                entityNote.getTitle(),
                entityNote.getContent(),
                entityNote.getCreationDate(),
                entityNote.getOwner().getUsername());
    }
}
