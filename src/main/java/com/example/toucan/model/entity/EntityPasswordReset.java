package com.example.toucan.model.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "password_reset")
public class EntityPasswordReset {

    public EntityPasswordReset() {}

    public EntityPasswordReset(String token, EntityUser owner, long expiryDate) {
        this.token = token;
        this.owner = owner;
        this.expiryDate = expiryDate;
    }

    @Id
    @Column(name = "uuid_password_reset", length = 16, unique = true, nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @OneToOne(mappedBy = "passwordResetToken", targetEntity = EntityUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private EntityUser owner;

    @Column(name = "expiry_date", nullable = false)
    private long expiryDate;

    public UUID getUuid() {
        return uuid;
    }

    public String getToken() {
        return token;
    }

    public EntityUser getOwner() {
        return owner;
    }

    public long getExpiryDate() {
        return expiryDate;
    }
}
