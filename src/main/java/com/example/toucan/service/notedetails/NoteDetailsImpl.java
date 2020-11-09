package com.example.toucan.service.notedetails;

import java.util.UUID;

public class NoteDetailsImpl implements NoteDetails {

    private UUID uuid;
    private String title;
    private String content;
    private long creationTimestamp;
    private String ownerUsername;

    public NoteDetailsImpl(
            UUID uuid,
            String title,
            String content,
            long creationTimestamp,
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
    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    @Override
    public String getOwnerUsername() {
        return ownerUsername;
    }

    /***********************************************************************
     * SETTERS AND CONSTRUCTOR FOR {@link org.modelmapper.ModelMapper}  /***
    /***/                                                               /***/
    public NoteDetailsImpl() {}                                         /***/
                                                                        /***/
    public void setUuid(UUID uuid) {                                  /***/
        this.uuid = uuid;                                               /***/
    }                                                                   /***/
                                                                        /***/
    public void setTitle(String title) {                                /***/
        this.title = title;                                             /***/
    }                                                                   /***/
                                                                        /***/
    public void setContent(String content) {                            /***/
        this.content = content;                                         /***/
    }                                                                   /***/
                                                                        /***/
    public void setCreationTimestamp(long creationTimestamp) {          /***/
        this.creationTimestamp = creationTimestamp;                     /***/
    }                                                                   /***/
                                                                        /***/
    public void setOwnerUsername(String ownerUsername) {                /***/
        this.ownerUsername = ownerUsername;                             /***/
    }                                                                   /***/
    /***********************************************************************/
}
