package com.opportunitywatcher.tradesystem.jpa;

import com.opportunitywatcher.tradesystem.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    @Query("select u from User u, Subscription s where s.user = u and s.identifier = ?1")
    Optional<User> findBySubscription(String subscriptionId);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
