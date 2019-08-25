package com.opportunitywatcher.tradesystem.autobot;

import com.opportunitywatcher.tradesystem.model.Autobot;

/**
 * Inteface to allow the individual instances of the autobots to be controlled and managed.
 */
public interface AutobotController {
    AutobotStatus start(final Autobot config);
}
