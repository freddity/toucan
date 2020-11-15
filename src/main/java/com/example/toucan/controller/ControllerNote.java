package com.example.toucan.controller;

import com.example.toucan.model.dto.DtoNote;
import com.example.toucan.model.dto.DtoShortNoteContainer;
import com.example.toucan.service.ServiceNote;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.UUID;

/**
 * This controller contains secured endpoints that allow an user to manage their notes.
 * Secured mean that user have to send correct JWT token in "Authorization" header to be permissed to these endpoints.
 */
@RestController
@RequestMapping("/")
public class ControllerNote {

    private final ServiceNote serviceNote;

    /**
     * A constructor with autoinjected constant.
     * @param serviceNote busines logic for notes.
     */
    public ControllerNote(ServiceNote serviceNote) {
        this.serviceNote = serviceNote;
    }

    /**
     * This endpoint allow to get note from the database.
     * @param username of note owner selected in request.
     * @param id note uuid.
     * @return a note from the database.
     */
    @GetMapping("/{username}/{id}")
    public DtoNote getNote(@PathVariable("username") String username,
                           @PathVariable("id") UUID id) {
        return serviceNote.getNote(id);
    }

    /**
     * This endpoint allow to take an notes from the database but user can specify pagination and quantity.
     * @param username of note owner selected in request
     * @param page pagination that will be used in SQL query to take proper notes.
     * @param size amount of notes to take .
     * @return {@link DtoShortNoteContainer} containing a list with taken notes.
     */
    @GetMapping("/{username}/thumbnails")
    public DtoShortNoteContainer getShortNotes(@PathVariable("username") String username,
                                               @RequestParam("page") int page,
                                               @RequestParam("size") int size) {
        return serviceNote.getShortNotesProvider(username, page, size);
    }

    /**
     * This endpoint allow to create a new note and write it into the database.
     * @param username of note owner selected in request.
     * @param dtoNote JSON as Java object from user containing a note to put into the database.
     */
    @PostMapping("/{username}/create")
    public void createNote(@PathVariable("username") String username,
                           @RequestBody @NotNull DtoNote dtoNote) {
        serviceNote.createNote(username, dtoNote.getTitle(), dtoNote.getContent());
    }

    /**
     * This endpoint allow to permanently delete a note.
     * @param username of note owner selected in request.
     * @param id note uuid.
     */
    @DeleteMapping("/{username}/{id}")
    public void deleteNote(@PathVariable("username") String username,
                           @PathVariable("id") UUID id) {
        serviceNote.deleteNote(id);
    }

    /**
     * This endpoint allow to update/edit a note.
     * @param id note uuid.
     * @param dtoNote JSON as Java object from user containing a changed note to update it in the database.
     */
    @PatchMapping("/{username}/{id}")
    public void updateNote(@PathVariable("id") UUID id,
                           @RequestBody @NotNull DtoNote dtoNote) {
        serviceNote.updateNote(id, dtoNote);
    }
}

/*
 use consumes={"application/json"} in the future
*/