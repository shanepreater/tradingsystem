package com.opportunitywatcher.tradesystem;

import com.opportunitywatcher.tradesystem.model.Subscription;
import com.opportunitywatcher.tradesystem.model.SubscriptionPlan;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {
    SubscriptionPlan createNewPlan(final SubscriptionPlan plan);

    Optional<SubscriptionPlan> activatePlan(final String planId);

    Optional<SubscriptionPlan> deactivatePlan(final String planId);

    Optional<SubscriptionPlan> removePlan(final String planId);

    Optional<Subscription> subscribeUser(final String userId, final int paymentDay, final SubscriptionPlan plan);

    Optional<Subscription> associateSubscription(final String subscriptionId, final String externalId, final Subscription.Status status);

    Optional<Subscription> updateSubscriptionStatus(final String subscriptionId, final Subscription.Status status);

    Optional<Subscription> removeSubscription(final String userId, final String subscriptionId);

    List<SubscriptionPlan> findSubscriptionPlans(final String promoCode);

    List<SubscriptionPlan> findSubscriptionPlans(final SubscriptionPlan.Status status);

    Optional<SubscriptionPlan> findPlan(final String planId);

    Optional<Subscription> findSubscriptionForUser(String userId);
}
