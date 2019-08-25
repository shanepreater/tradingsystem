package com.opportunitywatcher.tradesystem.jpa;

import com.opportunitywatcher.tradesystem.SubscriptionException;
import com.opportunitywatcher.tradesystem.UserService;
import com.opportunitywatcher.tradesystem.model.Subscription;
import com.opportunitywatcher.tradesystem.model.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceBean implements UserService {

    private final UserRepository userRepository;

    public UserServiceBean(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findUser(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    @Transactional
    public Optional<User> addSubscription(String userId, Subscription subscription) {
        final Optional<User> optionalUser = userRepository.findById(userId);
        optionalUser.ifPresent(user -> {
            subscription.setUser(user);
            final List<Subscription> subscriptions = user.getSubscriptions();
            if (subscriptions.isEmpty()) {
                subscriptions.add(subscription);
            } else {
                final boolean canRemove = subscriptions.stream().allMatch(oldSub -> oldSub.getStatus().isRemoveable());
                if (!canRemove) {
                    throw new SubscriptionException("User " + userId + " already has a subscription");
                }
                user.setSubscriptions(Collections.singletonList(subscription));
            }
            userRepository.save(user);
        });
        return optionalUser;
    }

    @Override
    public Optional<User> findUserForSubscription(String subscriptionId) {
        return userRepository.findBySubscription(subscriptionId);
    }

    @Override
    @Transactional
    public Optional<User> removeUser(String userId) {
        final Optional<User> optionalUser = userRepository.findById(userId);
        optionalUser.ifPresent((user) -> userRepository.delete(user));
        return optionalUser;
    }

    @Override
    @Transactional
    public User createUser(final User user) {
        if(StringUtils.isEmpty(user.getIdentifier())) {
            user.setIdentifier(UUID.randomUUID().toString());
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean userWithEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public void acceptDisclaimer(String userId, String disclaimerVersion) {
        userRepository.findById(userId)
                .ifPresent(user -> user.setDisclaimerVersionAccepted(disclaimerVersion));
    }
}
