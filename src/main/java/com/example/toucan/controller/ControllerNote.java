package com.example.toucan.controller;

import com.example.toucan.model.dto.DtoNote;
import com.example.toucan.service.ServiceNote;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/toucan/note")
public class ControllerNote {

    private final ServiceNote serviceNote;

    public ControllerNote(ServiceNote serviceNote) {
        this.serviceNote = serviceNote;
    }

    @GetMapping("/{id}")
    public DtoNote getNote(@PathVariable("id") UUID uuid) {
        return serviceNote.getNote(uuid);
    }

    @PostMapping
    public void createNote(@RequestHeader(name="Authorization") String token,
                           @RequestBody DtoNote dtoNote) {
        serviceNote.createNote(token, dtoNote.getTitle(), dtoNote.getContent());
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable("id") String id) {
        //serviceNote.
    }
}
