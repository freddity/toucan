package com.example.toucan.service.notedetails;

import java.util.UUID;

public class NoteDetailsImpl implements NoteDetails {

    private UUID uuid;
    private String title;
    private String content;
    private String creationTimestamp;
    private String ownerUsername;

    public NoteDetailsImpl(
            UUID uuid,
            String title,
            String content,
            String creationTimestamp,
            String ownerUsername) {
        this.uuid = uuid;
        this.title = title;
        this.content = content;
        this.creationTimestamp = creationTimestamp;
        this.ownerUsername = ownerUsername;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getCreationTimestamp() {
        return creationTimestamp;
    }

    @Override
    public String getOwnerUsername() {
        return ownerUsername;
    }
}
