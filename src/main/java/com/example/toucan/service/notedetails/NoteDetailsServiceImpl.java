package com.example.toucan.service.notedetails;

import com.example.toucan.exception.NoteNotFoundException;
import com.example.toucan.model.entity.EntityNote;
import com.example.toucan.repository.RepositoryNote;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NoteDetailsServiceImpl implements NoteDetailsService {

    private final RepositoryNote repositoryNote;
    private final ModelMapper modelMapper;

    public NoteDetailsServiceImpl(RepositoryNote repositoryNote, ModelMapper modelMapper) {
        this.repositoryNote = repositoryNote;
        this.modelMapper = modelMapper;
    }

    @Override
    public NoteDetailsImpl loadNoteByUUID(String uuid) throws NoteNotFoundException {
        EntityNote entityNote = repositoryNote.findByUuid(uuid);
        if (entityNote == null) throw new NullPointerException();
        return modelMapper.map(entityNote, NoteDetailsImpl.class);
    }
}
