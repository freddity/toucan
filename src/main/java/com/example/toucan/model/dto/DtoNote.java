package com.example.toucan.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

public class DtoNote implements Serializable {

    private UUID uuid;
    private String title;
    private String content;
    private String creationTimestamp;
    private String ownerUsername;

    /**
     * Constructor used to return a note to client
     * @param uuid
     * @param title
     * @param content
     * @param creationTimestamp
     * @param ownerUsername
     */
    public DtoNote(@JsonProperty("uuid") @NotBlank UUID uuid,
                   @JsonProperty("title") @NotBlank String title,
                   @JsonProperty("content") @NotBlank String content,
                   @JsonProperty("creationtimestamp") @NotBlank String creationTimestamp,
                   @JsonProperty("owner") @NotBlank String ownerUsername) {
        this.uuid = uuid;
        this.title = title;
        this.content = content;
        this.creationTimestamp = creationTimestamp;
        this.ownerUsername = ownerUsername;
    }

    /**
     * Constructor used to create a note
     * @param title
     * @param content
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

    public String getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(String creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }
}
