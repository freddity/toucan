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
@RequestMapping("/toucan/note")
public class ControllerNote {

    private final ServiceNote serviceNote;

    public ControllerNote(ServiceNote serviceNote) {
        this.serviceNote = serviceNote;
    }

    @GetMapping("/full")
    public DtoNote getNote(@RequestParam("id") UUID uuid) {
        return serviceNote.getNote(uuid);
    }

    @GetMapping("/thumbnails")
    public DtoShortNoteContainer getShortNotes(@RequestHeader(name="Authorization") String token,
                                               @RequestParam("page") int page,
                                               @RequestParam("size") int size) {
        return serviceNote.getShortNotesProvider(token, page, size);
    }

    @PostMapping(consumes={"application/json"})
    public void createNote(@RequestHeader(name="Authorization") String token,
                           @RequestBody DtoNote dtoNote) {
        serviceNote.createNote(token, dtoNote.getTitle(), dtoNote.getContent());
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable("id") UUID id) {
        serviceNote.deleteNote(id);
    }

    @PatchMapping("/{id}")
    public void updateNote(@PathVariable("id") String id) {
        //serviceNote.
    }
}
