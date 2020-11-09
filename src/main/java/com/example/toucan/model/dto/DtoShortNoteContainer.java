package com.example.toucan.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

public class DtoShortNoteContainer implements Serializable {

    @NotBlank
    private List<DtoNote> noteList;

    public DtoShortNoteContainer(@JsonProperty("thumbnails") List<DtoNote> noteList) {
        this.noteList = noteList;
    }

    /****************************************************************
     * SETTER AND GETTER FOR {@link org.modelmapper.ModelMapper} /***
     **/                                                         /***/
    public List<DtoNote> getNoteList() {                         /***/
        return noteList;                                         /***/
    }                                                            /***/
                                                                 /***/
    public void setNoteList(List<DtoNote> noteList) {            /***/
        this.noteList = noteList;                                /***/
    }                                                            /***/
    /****************************************************************/
}
