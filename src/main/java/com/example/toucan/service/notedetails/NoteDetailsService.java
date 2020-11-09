package com.example.toucan.service.notedetails;

import com.example.toucan.exception.NoteNotFoundException;

import java.util.UUID;

public interface NoteDetailsService {

    NoteDetails loadNoteByUUID(UUID uuid) throws NoteNotFoundException;
}
