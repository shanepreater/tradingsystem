package com.opportunitywatcher.tradesystem.model;

import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Data
public class Trade {
    private final String tradeId;
    private final String parentId;
    private final int units;
    private final Double risk;
    private final Double initialRisk;
    private final Double amountAtRisk;
    private final Double stop;
    private final Double target;
    private final Double trailStop;
    private final Instant opened;
    private final Double openPrice;
    private final TimeFrame timeframe;
    private final Instrument instrument;
    private final Instant closed;
    private final Double closedPrice;
    private final Double amountAchieved;
    private final Double initialStop;
    private final Map<String, Object> extras;

    protected Trade(String tradeId, String parentId, int units, Double risk, Double initialRisk, Double amountAtRisk, Double stop, Double target, Double trailStop, Instant opened, Double openPrice, TimeFrame timeframe, Instrument instrument, Instant closed, Double closedPrice, Double amountAchieved, Double initialStop, Map<String, Object> extras) {
        this.tradeId = tradeId;
        this.parentId = parentId;
        this.units = units;
        this.risk = risk;
        this.initialRisk = initialRisk;
        this.amountAtRisk = amountAtRisk;
        this.stop = stop;
        this.target = target;
        this.trailStop = trailStop;
        this.opened = opened;
        this.openPrice = openPrice;
        this.timeframe = timeframe;
        this.instrument = instrument;
        this.closed = closed;
        this.closedPrice = closedPrice;
        this.amountAchieved = amountAchieved;
        this.initialStop = initialStop;
        this.extras = extras;
    }

    public static TradeBuilder builder() {
        return new TradeBuilder();
    }
}
