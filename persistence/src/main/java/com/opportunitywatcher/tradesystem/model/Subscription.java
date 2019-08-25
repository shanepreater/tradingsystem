package com.opportunitywatcher.tradesystem.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer identifier;

    @ManyToOne(targetEntity = User.class)
    private User user;

    private Instant startDate;
    private Instant expiryDate;
    private String promotionalCode;
    private double monthlyCost;
    private int paymentDay;
    private boolean requiresPayments;
    private String subscriptionId;
    @ManyToOne(targetEntity = SubscriptionPlan.class)
    private SubscriptionPlan plan;
    private Status status;
    private Integer maxAutobots;

    public enum Status {
        APPOVAL_PENDING(false),
        APPROVED(false),
        ACTIVE(false),
        SUSPENDED(true),
        CANCELLED(true),
        EXPIRED(true);

        private final boolean removeable;

        Status(final boolean removeable) {
            this.removeable = removeable;
        }

        public boolean isRemoveable() {
            return this.removeable;
        }
    }
}
