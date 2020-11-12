package com.example.toucan.controller;

import com.example.toucan.model.dto.DtoNote;
import com.example.toucan.model.dto.DtoShortNoteContainer;
import com.example.toucan.service.ServiceNote;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class ControllerNote {

    private final ServiceNote serviceNote;

    public ControllerNote(ServiceNote serviceNote) {
        this.serviceNote = serviceNote;
    }

    @GetMapping("/{username}/{id}")
    public DtoNote getNote(@PathVariable("username") String username,
                           @PathVariable("id") UUID id) {
        return serviceNote.getNote(id);
    }

    @GetMapping("/{username}/thumbnails")
    public DtoShortNoteContainer getShortNotes(@PathVariable("username") String username,
                                               @RequestHeader("Authorization") String token,
                                               @RequestParam("page") int page,
                                               @RequestParam("size") int size) {
        return serviceNote.getShortNotesProvider(token, page, size);
    }

    @PostMapping("/{username}/create")
    public void createNote(@PathVariable("username") String username,
                           @RequestHeader(name="Authorization") String token,
                           @RequestBody DtoNote dtoNote) {
        serviceNote.createNote(token, dtoNote.getTitle(), dtoNote.getContent());
    }

    @DeleteMapping("/{username}/{id}")
    public void deleteNote(@PathVariable("username") String username,
                           @PathVariable("id") UUID id) {
        serviceNote.deleteNote(id);
    }

    @PatchMapping("/{username}/{id}")
    public void updateNote(@PathVariable("username") String username,
                           @PathVariable("id") UUID id) {
        //serviceNote.
    }

    /*
    use consumes={"application/json"} in the future
     */
}
