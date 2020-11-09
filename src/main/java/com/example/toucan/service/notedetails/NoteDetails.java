package com.example.toucan.service.notedetails;

import java.io.Serializable;

public interface NoteDetails extends Serializable {

    String getUUID();

    String getTitle();

    String getContent();

    long getCreationTimestamp();

    String getOwnerUsername();
}
