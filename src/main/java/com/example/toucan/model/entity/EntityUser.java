package com.example.toucan.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "USERS")
public class EntityUser {

    public EntityUser(){}

    public EntityUser(String email, String username, String password){
        this.email = email;
        this.username = username;
        this.password = password;
        this.blockedStatus = 0;
    }

    @Id
    @Column(name = "uuid_user", length = 16, unique = true, nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "blocked_status", nullable = false)
    private int blockedStatus; //0 = not blocked
                               //1 = blocked

    @OneToMany(targetEntity=EntityNote.class, mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EntityNote> noteList = new ArrayList<>();


    public UUID getUuid() {
        return uuid;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getBlockedStatus() {
        return blockedStatus;
    }

    public List<EntityNote> getNoteList() {
        return noteList;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int setBlockedStatus(int blockedStatus) {
        return this.blockedStatus = blockedStatus;
    }

    public void setNoteList(List<EntityNote> noteList) {
        this.noteList = noteList;
    }
}
