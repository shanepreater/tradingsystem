package com.opportunitywatcher.tradesystem;

import com.opportunitywatcher.tradesystem.model.Subscription;
import com.opportunitywatcher.tradesystem.model.User;

import java.util.Optional;

/**
 * Handle interactions with the User store.
 */
public interface UserService {
    Optional<User> findUser(final String userId);

    Optional<User> addSubscription(final String userId, final Subscription subscription);

    Optional<User> findUserForSubscription(final String subscriptionId);

    Optional<User> removeUser(final String userId);

    User createUser(final User user);

    Optional<User> findByEmail(final String email);

    boolean userWithEmailExists(final String email);

    void acceptDisclaimer(final String userId, final String disclaimerVersion);
}
