package com.opportunitywatcher.tradesystem.controller;

import com.opportunitywatcher.tradesystem.NotFoundException;
import com.opportunitywatcher.tradesystem.SubscriptionService;
import com.opportunitywatcher.tradesystem.model.SubscriptionPlan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/api/plan", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PlanController {

    private final SubscriptionService subscriptionService;

    public PlanController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @RequestMapping("plans")
    @PreAuthorize("hasRole('USER')")
    public List<SubscriptionPlan> findAvailablePlans(@RequestParam("promo") final String promoCode) {
        return subscriptionService.findSubscriptionPlans(promoCode);
    }

    @RequestMapping(value = "plans/{planId}", method = RequestMethod.GET)
    public SubscriptionPlan getPlan(@PathVariable("planId") final String planId) {
        final Optional<SubscriptionPlan> optionalPlan = subscriptionService.findPlan(planId);
        return optionalPlan.orElseThrow(() -> new NotFoundException("Unable to find plan with id: " + planId));
    }
}
