package com.example.toucan.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "NOTES")
public class EntityNote {

    public EntityNote(){}

    public EntityNote(String title, String content, Date creationDate){
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
    }

    public EntityNote(String title, String content, Date creationDate, EntityUser owner){
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.owner = owner;
    }

    @Id
    @Column(name = "uuid_note", length = 16, unique = true, nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(name =  "title", nullable = false)
    private String title;

    @Column(name =  "content", nullable = false)
    private String content;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private EntityUser owner;


    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreationDate() {
        return creationDate;
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
