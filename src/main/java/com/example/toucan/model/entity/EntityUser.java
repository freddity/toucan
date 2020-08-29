package com.example.toucan.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "USERS")
public class EntityUser {

    @Id
    @Column(length = 16, unique = true, nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "blocked_status", nullable = false)
    private int blockedStatus; //0 = not blocked
                               //1 = blocked


}
