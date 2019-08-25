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
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/api/admin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final SubscriptionService subscriptionService;

    public AdminController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @RequestMapping(value = "plans/{planId}/activate", method = RequestMethod.POST)
    public SubscriptionPlan activatePlan(@PathVariable("planId") final String planId, Principal principal) {
        log.info("Plan {} is being activated by {}", planId, principal.getName());
        final Optional<SubscriptionPlan> optionalPlan = subscriptionService.activatePlan(planId);
        return optionalPlan.orElseThrow(() -> new NotFoundException("Unable to find plan with id: " + planId));
    }

    @RequestMapping(value = "plans/{planId}/deactivate", method = RequestMethod.POST)
    public SubscriptionPlan deactivatePlan(@PathVariable("planId") final String planId, Principal principal) {
        log.info("Plan {} is being activated by {}", planId, principal.getName());
        final Optional<SubscriptionPlan> optionalPlan = subscriptionService.deactivatePlan(planId);
        return optionalPlan.orElseThrow(() -> new NotFoundException("Unable to find plan with id: " + planId));
    }

    @RequestMapping(value = "plans", method = RequestMethod.GET)
    public List<SubscriptionPlan> findPlans(@RequestParam(value = "status", defaultValue = "ACTIVE") final SubscriptionPlan.Status status, Principal principal) {
        log.info("{} has requested the list of plans with status: {}", principal.getName(), status);
        return subscriptionService.findSubscriptionPlans(status);
    }
}
