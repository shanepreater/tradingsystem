package com.opportunitywatcher.tradesystem.jpa;

import com.opportunitywatcher.tradesystem.SubscriptionService;
import com.opportunitywatcher.tradesystem.model.Subscription;
import com.opportunitywatcher.tradesystem.model.SubscriptionPlan;
import com.opportunitywatcher.tradesystem.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionServiceBean implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final SubscriptionPlanRepository subscriptionPlanRepository;

    private final UserRepository userRepository;

    public SubscriptionServiceBean(SubscriptionRepository subscriptionRepository, SubscriptionPlanRepository subscriptionPlanRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionPlanRepository = subscriptionPlanRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<SubscriptionPlan> findPlan(final String planId) {
        return subscriptionPlanRepository.findByIdentiferAndStatus(planId, SubscriptionPlan.Status.ACTIVE);
    }

    @Override
    public Optional<Subscription> findSubscriptionForUser(String userId) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public SubscriptionPlan createNewPlan(SubscriptionPlan plan) {
        plan.setStatus(SubscriptionPlan.Status.CREATED);
        return subscriptionPlanRepository.save(plan);
    }

    @Override
    @Transactional
    public Optional<SubscriptionPlan> activatePlan(String planId) {
        Optional<SubscriptionPlan> optionalPlan = subscriptionPlanRepository.findById(planId);
        optionalPlan.ifPresent(plan -> plan.setStatus(SubscriptionPlan.Status.ACTIVE));
        return optionalPlan;
    }

    @Override
    @Transactional
    public Optional<SubscriptionPlan> deactivatePlan(String planId) {
        Optional<SubscriptionPlan> optionalPlan = subscriptionPlanRepository.findById(planId);
        optionalPlan.ifPresent(plan -> plan.setStatus(SubscriptionPlan.Status.INACTIVE));
        return optionalPlan;
    }

    @Override
    @Transactional
    public Optional<SubscriptionPlan> removePlan(String planId) {
        final Optional<SubscriptionPlan> optionalPlan = subscriptionPlanRepository.findById(planId);
        optionalPlan.ifPresent(plan -> subscriptionPlanRepository.delete(plan));
        return optionalPlan;
    }

    @Override
    @Transactional
    public Optional<Subscription> subscribeUser(String userId, int paymentDay, SubscriptionPlan plan) {
        Optional<User> optionalUser = userRepository.findById(userId);
        final Optional<Subscription> subscription = optionalUser.map(user -> buildSubscription(user, plan, paymentDay));
        return subscription;
    }

    @Override
    @Transactional
    public Optional<Subscription> associateSubscription(String subscriptionId, String externalId, Subscription.Status status) {
        Optional<Subscription> optionalSubscription = subscriptionRepository.findById(subscriptionId);
        optionalSubscription.ifPresent(subscription -> updateSubscription(externalId, status, subscription));
        return optionalSubscription;
    }

    public void updateSubscription(String externalId, Subscription.Status status, Subscription subscription) {
        subscription.setSubscriptionId(externalId);
        subscription.setStatus(status);
    }

    private Subscription buildSubscription(User user, SubscriptionPlan plan, int paymentDay) {
        Subscription s = new Subscription();
        s.setUser(user);
        s.setPlan(plan);
        s.setMonthlyCost(plan.getMonthlyAmount());
        s.setPaymentDay(paymentDay);
        s.setPromotionalCode(plan.getPromoCode());
        s.setRequiresPayments(plan.isChargeable());
        if (plan.isChargeable()) {
            s.setStatus(Subscription.Status.APPOVAL_PENDING);
        } else {
            s.setStatus(Subscription.Status.ACTIVE);
        }
        s.setStartDate(Instant.now());
        s.setExpiryDate(Instant.now().plus(1, ChronoUnit.YEARS));
        plan.getSubscriptions().add(s);
        user.getSubscriptions().add(s);
        return subscriptionRepository.save(s);
    }

    @Override
    @Transactional
    public Optional<Subscription> updateSubscriptionStatus(String subscriptionId, Subscription.Status status) {
        Optional<Subscription> optionalSubscription = subscriptionRepository.findBySubscriptionId(subscriptionId);
        optionalSubscription.ifPresent(subscription -> subscription.setStatus(status));
        return optionalSubscription;
    }

    @Override
    @Transactional
    public Optional<Subscription> removeSubscription(String userId, String subscriptionId) {
        final Optional<Subscription> optionalSubscription = userRepository.findById(userId).map(user -> subscriptionRepository.findBySubscriptionIdAndUser(subscriptionId, user).get());
        optionalSubscription.ifPresent(subscription -> subscriptionRepository.delete(subscription));
        return optionalSubscription;
    }

    @Override
    public List<SubscriptionPlan> findSubscriptionPlans(String promoCode) {
        return subscriptionPlanRepository.findAllByPromoCodeAndStatus(promoCode, SubscriptionPlan.Status.ACTIVE);
    }

    @Override
    public List<SubscriptionPlan> findSubscriptionPlans(SubscriptionPlan.Status status) {
        return subscriptionPlanRepository.findAllByStatus(status);
    }
}
