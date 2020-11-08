package com.example.toucan.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

/**
 * DTO used to transfer information about note in and outside the system
 */
public class DtoNote implements Serializable {

    private UUID uuid;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private long creationTimestamp;
    private String ownerUsername;

    /**
     * Constructor used to return a note to client
     * @param uuid note identifier
     * @param title note title
     * @param content note content
     * @param creationTimestamp note creation date in unix timestamp (in seconds)
     * @param ownerUsername note owner/creator username
     */
    public DtoNote(@JsonProperty("uuid") @NotBlank UUID uuid,
                   @JsonProperty("title") @NotBlank String title,
                   @JsonProperty("content") @NotBlank String content,
                   @JsonProperty("creationtimestamp") @NotBlank long creationTimestamp,
                   @JsonProperty("owner") @NotBlank String ownerUsername) {
        this.uuid = uuid;
        this.title = title;
        this.content = content;
        this.creationTimestamp = creationTimestamp;
        this.ownerUsername = ownerUsername;
    }

    /**
     * Constructor used to create a note
     * @param title note title
     * @param content note content
     */
    public DtoNote(@JsonProperty("title") @NotBlank String title,
                   @JsonProperty("content") @NotBlank String content) {
        this.title = title;
        this.content = content;
    }

    /**
     * Non-private non-argument constructor only for {@link org.modelmapper.ModelMapper}
     */
    public DtoNote() {}

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }
}
