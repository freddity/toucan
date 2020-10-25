package com.example.toucan.service;

import com.example.toucan.model.dto.DtoNote;
import com.example.toucan.model.entity.EntityNote;
import com.example.toucan.repository.RepositoryNote;
import com.example.toucan.repository.RepositoryUser;
import com.example.toucan.service.notedetails.NoteDetails;
import com.example.toucan.service.notedetails.NoteDetailsImpl;
import com.example.toucan.service.notedetails.NoteDetailsServiceImpl;
import com.example.toucan.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class ServiceNote {

    private final RepositoryUser repositoryUser;
    private final NoteDetailsServiceImpl noteDetailsService;
    private final RepositoryNote repositoryNote;
    private ModelMapper modelMapper;
    private final JwtUtil jwtUtil;

    public ServiceNote(RepositoryUser repositoryUser, NoteDetailsServiceImpl noteDetailsService, RepositoryNote repositoryNote, ModelMapper modelMapper) {
        this.repositoryUser = repositoryUser;
        this.noteDetailsService = noteDetailsService;
        this.repositoryNote = repositoryNote;
        //this.modelMapper = modelMapper;
        this.jwtUtil = new JwtUtil();

        modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<NoteDetailsImpl, DtoNote>() {
            @Override
            protected void configure() {
                map().setUuid(source.getUUID());
                map().setTitle(source.getTitle());
                map().setContent(source.getContent());
                map().setCreationTimestamp(source.getCreationTimestamp());
                map().setOwnerUsername(source.getOwnerUsername());
            }
        });
    }

    public DtoNote getNote(UUID uuid) {
        if (noteDetailsService.loadNoteByUUID(uuid) != null) {
            //return modelMapper.map(noteDetailsService.loadNoteByUUID(uuid), DtoNote.class);
            NoteDetailsImpl noteDetails = noteDetailsService.loadNoteByUUID(uuid);
            return new DtoNote(noteDetails.getUUID(),
                    noteDetails.getTitle(),
                    noteDetails.getContent(),
                    noteDetails.getCreationTimestamp(),
                    noteDetails.getOwnerUsername());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void createNote(String token, String title, String content) {
        repositoryNote.save(
                new EntityNote(
                        title,
                        content,
                        Long.toString(System.currentTimeMillis()),
                        repositoryUser.findByUsername(jwtUtil.extractUsername(token.substring(7)))));
    }

    //todo create filter for ControllerNote
}
