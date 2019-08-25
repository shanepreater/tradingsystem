package com.opportunitywatcher.tradesystem.controller;

import com.opportunitywatcher.tradesystem.*;
import com.opportunitywatcher.tradesystem.model.Autobot;
import com.opportunitywatcher.tradesystem.model.Subscription;
import com.opportunitywatcher.tradesystem.model.Trade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/api/trades", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TradeController {
    private final TradeService tradeService;

    private final SubscriptionService subscriptionService;

    public TradeController(TradeService tradeService, SubscriptionService subscriptionService) {
        this.tradeService = tradeService;
        this.subscriptionService = subscriptionService;
    }

    @RequestMapping(value = "{accountId}", method = RequestMethod.GET)
    public List<Trade> getTrades(
            @PathVariable("accountId") final String accountId,
            @RequestParam(value = "open", defaultValue = "false") final boolean openOnly,
            Principal principal) {
        String userName = principal.getName();
        if (openOnly) {
            return tradeService.findOpenTradesForAccount(accountId, userName);
        }
        return tradeService.findTradesForAccount(accountId, userName);
    }

    @RequestMapping(value = "{accountId}/config", method = RequestMethod.GET)
    public Autobot getAutobot(@PathVariable("accountId") final String accountId, Principal principal) {
        Optional<Autobot> optionalAutobot = tradeService.findConfig(accountId, principal.getName());
        return optionalAutobot.orElseThrow(() -> new NotFoundException("Unable to find config for account number: " + accountId));
    }

    @RequestMapping(value = "{accountId}/config", method = RequestMethod.PUT)
    public Autobot updateAutobot(@PathVariable("accountId") final String accountId, @RequestBody final Autobot updated, Principal principal) {
        log.info("User: {} is updating the autobot config associated with account {}", principal.getName(), accountId);
        final Optional<Autobot> config = tradeService.findConfig(accountId, principal.getName());
        config.ifPresent(autobot -> updateAutobot(autobot, updated));
        return config.orElseThrow(() -> new NotFoundException("Unable to find autobot config associated with id: " + accountId));
    }

    private Autobot updateAutobot(final Autobot stored, final Autobot updated) {
        if (stored.getStatus() == Autobot.Status.RUNNING) {
            throw new ActiveAutobotException("Changes can only be made to stopped autobot instances");
        } else if (stored.getStatus() == Autobot.Status.STARTING ||
                stored.getStatus() == Autobot.Status.STOPPING) {
            throw new ActiveAutobotException("Autobot is changing state please wait before attempting to update");
        }
        stored.setConfigJson(updated.getConfigJson());
        stored.setStrategy(updated.getStrategy());
        if (!StringUtils.isEmpty(updated.getEncryptedSecretKey())) {
            stored.setEncryptedSecretKey(updated.getEncryptedSecretKey());
        }
        return stored;
    }

    @RequestMapping(value = "{accountId}/config", method = RequestMethod.POST)
    public Autobot createAutbotConfig(@PathVariable("accountId") final String accountId, @RequestBody final Autobot config, Principal principal) {
        log.info("User: {} is creating an autobot config associated with account {}", principal.getName(), accountId);
        final Optional<Autobot> existing = tradeService.findConfig(accountId, principal.getName());
        if (existing.isPresent()) {
            throw new ActiveAutobotException("Config already exists for account: " + accountId);
        }

        if(config.isLive()) {
            Subscription sub = subscriptionService.findSubscriptionForUser(principal.getName())
                    .orElseThrow(() -> new SubscriptionException("You need a subscription to configure a live bot."));
            if(sub.getStatus() != Subscription.Status.ACTIVE) {
                throw new SubscriptionException("Your subscription is not active.");
            }
        }
        return tradeService.saveConfig(config, principal.getName());
    }
}
