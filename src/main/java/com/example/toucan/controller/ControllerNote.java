package com.example.toucan.controller;

import com.example.toucan.model.dto.DtoNote;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/toucan/note")
public class ControllerNote {

    @GetMapping("/{id}")
    public void getNote(@PathVariable("id") String noteId) {

    }

    @PostMapping
    public void createNote(@RequestBody DtoNote dtoNote) {

    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable("id") String id) {

    }



}
