package com.opportunitywatcher.tradesystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.id.UUIDGenerationStrategy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Table(name = "user_details", uniqueConstraints = {
        @UniqueConstraint(name = "UniqueUserIDs", columnNames = {"id"})
})
public class User {
    @Id
    @Column(name = "id")
    private String identifier;

    private boolean admin = false;

    private boolean tester = false;

    @Email
    @Column(nullable = false)
    private String email;

    private boolean emailVerified;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private AuthProvider provider;

    @NotBlank
    @Column(nullable = false)
    private String providerId;

    private String imageUrl;

    /**
     * This is the version of the disclaimer component that the user has accepted.
     * If the version changes then it will reshow them to get continued acceptance.
     */
    private String disclaimerVersionAccepted="";

    @OneToMany(targetEntity = Subscription.class, cascade = CascadeType.ALL)
    private List<Subscription> subscriptions;

    @OneToMany(targetEntity = Autobot.class, cascade = CascadeType.ALL)
    private List<Autobot> autobots;
}
