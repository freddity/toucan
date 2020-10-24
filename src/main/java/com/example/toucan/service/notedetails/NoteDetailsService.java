package com.example.toucan.service.notedetails;

import com.example.toucan.exception.NoteNotFoundException;

import java.util.UUID;

public interface NoteDetailsService {

    NoteDetails loadNoteByUsername(UUID uuid) throws NoteNotFoundException;
}
