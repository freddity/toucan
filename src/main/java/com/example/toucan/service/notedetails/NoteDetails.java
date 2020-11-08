package com.example.toucan.service.notedetails;

import java.io.Serializable;
import java.util.UUID;

public interface NoteDetails extends Serializable {

    UUID getUUID();

    String getTitle();

    String getContent();

    long getCreationTimestamp();

    String getOwnerUsername();
}
