package com.opportunitywatcher.tradesystem.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TradeBuilderTest {
    @DisplayName("Test that all items results in a new Trade")
    @Test
    public void allItemsBuild() {
        TradeBuilder underTest = Trade.builder();
        assertNotNull(underTest);

        underTest.amountAchieved(10.5);
        underTest.amountAtRisk(22.5);
        underTest.initialRisk(220.22);
        final Trade trade = underTest.build();
        assertNotNull(trade);
    }
}