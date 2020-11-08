package com.example.toucan.model.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class EntityUser {

    @Transient
    private final String DEF_ROLE = "ROLE_USER";
    @Transient
    private final boolean DEF_LOCK_STATUS = false;

    public EntityUser(){}

    public EntityUser(String username, String password){
        this.username = username;
        this.password = password;
        this.role = DEF_ROLE;
        this.lockStatus = DEF_LOCK_STATUS;
    }

    @Id
    @Column(name = "uuid_user", length = 16, unique = true, nullable = false)
    private final UUID uuid = UUID.randomUUID();

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "lock_status", nullable = false)
    private boolean lockStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "password_reset_token", referencedColumnName = "uuid_password_reset")
    private EntityPasswordReset passwordResetToken;

    @OneToMany(targetEntity=EntityNote.class, mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EntityNote> noteList = new ArrayList<>();

    public UUID getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public boolean isLocked() {
        return lockStatus;
    }

    public EntityPasswordReset getPasswordResetToken() {
        return passwordResetToken;
    }

    public List<EntityNote> getNoteList() {
        return noteList;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setLockStatus(boolean lockStatus) {
        this.lockStatus = lockStatus;
    }

    public void setPasswordResetToken(EntityPasswordReset passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public void setNoteList(List<EntityNote> noteList) {
        this.noteList = noteList;
    }
}
