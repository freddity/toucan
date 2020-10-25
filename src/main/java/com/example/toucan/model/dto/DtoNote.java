package com.example.toucan.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

public class DtoNote implements Serializable {

    @NotBlank
    private UUID uuid;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String creationTimestamp;

    @NotBlank
    private String ownerUsername;

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
