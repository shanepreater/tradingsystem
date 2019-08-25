package com.opportunitywatcher.tradesystem.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Main object to hold the details on an individual autobot instance started by a user.
 */
@Data
@Entity
@Table(name = "autobot_config")
public class Autobot {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer identifier;

    @ManyToOne(targetEntity = User.class)
    private User user;

    private String strategy;

    private String configJson;

    private String encryptedSecretKey;

    private String accountNumber;

    private Status status;

    private boolean live;

    public enum Status {
        STARTING,
        RUNNING,
        STOPPING,
        STOPPED
    }

}
