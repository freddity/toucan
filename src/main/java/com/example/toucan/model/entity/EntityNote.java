package com.example.toucan.model.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "notes")
public class EntityNote {

    public EntityNote(){}

    public EntityNote(String title, String content, long creationTimestamp, EntityUser owner){
        this.title = title;
        this.content = content;
        this.creationTimestamp = creationTimestamp;
        this.owner = owner;
    }

    @Id
    @Column(name = "uuid_note", length = 16, unique = true, nullable = false)
    private String uuid = String.valueOf(UUID.randomUUID());

    @Column(name =  "title", nullable = false)
    private String title;

    @Column(name =  "content", nullable = false)
    private String content;

    @Column(name = "creation_timestamp", nullable = false)
    private long creationTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private EntityUser owner;


    public String getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public EntityUser getOwner() {
        return owner;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
