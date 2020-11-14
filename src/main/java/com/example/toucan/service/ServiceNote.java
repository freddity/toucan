package com.example.toucan.service;

import com.example.toucan.model.dto.DtoNote;
import com.example.toucan.model.dto.DtoShortNoteContainer;
import com.example.toucan.model.entity.EntityNote;
import com.example.toucan.repository.RepositoryNote;
import com.example.toucan.repository.RepositoryUser;
import com.example.toucan.service.notedetails.NoteDetailsServiceImpl;
import com.example.toucan.service.userdetails.UserDetailsServiceImpl;
import com.example.toucan.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ServiceNote {

    private final NoteDetailsServiceImpl noteDetailsService;
    private final UserDetailsServiceImpl userDetailsService;
    private final RepositoryNote repositoryNote;
    private final RepositoryUser repositoryUser;
    private final ModelMapper modelMapper;
    private final JwtUtil jwtUtil;

    public ServiceNote(NoteDetailsServiceImpl noteDetailsService, UserDetailsServiceImpl userDetailsService,
                       RepositoryNote repositoryNote, RepositoryUser repositoryUser, ModelMapper modelMapper) {
        this.noteDetailsService = noteDetailsService;
        this.userDetailsService = userDetailsService;
        this.repositoryNote = repositoryNote;
        this.repositoryUser = repositoryUser;
        this.modelMapper = modelMapper;
        this.jwtUtil = new JwtUtil();
    }

    public DtoNote getNote(UUID uuid) {
        if (noteDetailsService.loadNoteByUUID(uuid) != null) {
            return modelMapper.map(noteDetailsService.loadNoteByUUID(uuid), DtoNote.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void createNote(String token, String title, String content) {
        repositoryNote.save(new EntityNote(
                title,
                content,
                System.currentTimeMillis()/1000,
                repositoryUser.findByUsername(jwtUtil.extractUsername(token.substring(7)))));
    }

    public DtoShortNoteContainer getShortNotesProvider(String token, int page, int size) {
        return getShortNotes(jwtUtil.extractUsername(token.substring(7)), page, size);
    }

    private DtoShortNoteContainer getShortNotes(String username, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<EntityNote> entityList = repositoryNote.takeForShortNotes(repositoryUser.findByUsername(username).getUuid(), pageable);
        List<DtoNote> dtoList = new ArrayList<>();

        for(EntityNote n :entityList) {
            dtoList.add(modelMapper.map(n, DtoNote.class));
        }

        return new DtoShortNoteContainer(dtoList);
    }

    public void deleteNote(UUID id) {
        try {
            repositoryNote.delete(Objects.requireNonNull(repositoryNote.findByUuid(id)));
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "note with selected ID doesn't exist");
        }
    }

    public void updateNote(UUID uuid, DtoNote dtoNote) {
        repositoryNote.updateNote(dtoNote.getTitle(), dtoNote.getContent(), uuid);
    }


    //todo zrob tak zeby kazdy endpoint zwracal nowy token!!!
}
