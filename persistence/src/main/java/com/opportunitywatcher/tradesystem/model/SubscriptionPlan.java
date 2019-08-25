package com.opportunitywatcher.tradesystem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "subscription_plans")
public class SubscriptionPlan {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer identifer;

    private boolean chargeable =true;

    private String planId;

    private String productUUID;

    private String name;

    private String description;

    private Status status;

    private double monthlyAmount;

    private String promoCode;

    private Integer maxAutobots;

    @OneToMany(targetEntity = Subscription.class, cascade = CascadeType.ALL)
    private List<Subscription> subscriptions;

    public enum Status {
        ACTIVE,
        INACTIVE,
        CREATED
    }

}
