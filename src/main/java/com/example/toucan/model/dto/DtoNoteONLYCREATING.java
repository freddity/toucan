package com.example.toucan.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class DtoNoteONLYCREATING implements Serializable {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public DtoNoteONLYCREATING(@JsonProperty("title") @NotBlank String title,
                               @JsonProperty("content") @NotBlank String content) {
        this.title = title;
        this.content = content;
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
}
