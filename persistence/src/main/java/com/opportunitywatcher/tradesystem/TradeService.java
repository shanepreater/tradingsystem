package com.opportunitywatcher.tradesystem;

import com.opportunitywatcher.tradesystem.model.Autobot;
import com.opportunitywatcher.tradesystem.model.Trade;

import java.util.List;
import java.util.Optional;

public interface TradeService {
    List<Trade> findTradesForAccount(final String accountId, final String owningUser);

    List<Trade> findOpenTradesForAccount(final String accountId, final String owningUser);

    List<Trade> finalAllTradesForUser(final String userId);

    Optional<Autobot> findConfig(final String accountId, final String userId);

    Autobot saveConfig(Autobot config, String userId);
}
