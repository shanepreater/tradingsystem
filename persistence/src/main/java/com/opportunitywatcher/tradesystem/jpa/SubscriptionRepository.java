package com.opportunitywatcher.tradesystem.jpa;

import com.opportunitywatcher.tradesystem.model.Subscription;
import com.opportunitywatcher.tradesystem.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubscriptionRepository extends CrudRepository<Subscription, String> {
    Optional<Subscription> findBySubscriptionId(final String subscriptionId);
    Optional<Subscription> findBySubscriptionIdAndUser(final String subscription, final User user);
}
