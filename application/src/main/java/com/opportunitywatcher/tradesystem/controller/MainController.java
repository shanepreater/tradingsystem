package com.opportunitywatcher.tradesystem.controller;

import com.opportunitywatcher.tradesystem.*;
import com.opportunitywatcher.tradesystem.model.Autobot;
import com.opportunitywatcher.tradesystem.model.SubscriptionPlan;
import com.opportunitywatcher.tradesystem.model.Trade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/api/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MainController {
    private final TradeService tradeService;

    private final SubscriptionService subscriptionService;

    private final WebConfig webConfig;

    private final BuildProperties buildProperties;

    public MainController(TradeService tradeService, SubscriptionService subscriptionService, WebConfig webConfig, BuildProperties buildProperties) {
        this.tradeService = tradeService;
        this.subscriptionService = subscriptionService;
        this.webConfig = webConfig;
        this.buildProperties = buildProperties;
    }

    @RequestMapping(method = RequestMethod.GET)
    public WebConfig getConfig() {
        webConfig.setVersion(buildProperties.getVersion());
        return webConfig;
    }
}
