package com.opportunitywatcher.tradesystem.jpa;

import com.opportunitywatcher.tradesystem.model.SubscriptionPlan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionPlanRepository extends CrudRepository<SubscriptionPlan, String> {
    List<SubscriptionPlan> findAllByPromoCodeAndStatus(final String promoCode, final SubscriptionPlan.Status status);
    List<SubscriptionPlan> findAllByStatus(final SubscriptionPlan.Status status);
    Optional<SubscriptionPlan> findByIdentiferAndStatus(final String planId, final SubscriptionPlan.Status status);
}
