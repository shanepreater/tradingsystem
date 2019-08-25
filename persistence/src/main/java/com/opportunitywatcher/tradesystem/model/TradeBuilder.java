package com.opportunitywatcher.tradesystem.model;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class TradeBuilder {
    private  String tradeId;
    private  String parentId;
    private  int units;
    private  Double risk;
    private  Double initialRisk;
    private  Double amountAtRisk;
    private  Double stop;
    private  Double target;
    private  Double trailStop;
    private  Instant opened;
    private  Double openPrice;
    private  TimeFrame timeframe;
    private  Instrument instrument;
    private  Instant closed;
    private  Double closedPrice;
    private  Double amountAchieved;
    private  Double initialStop;
    private  final Map<String, Object> extras = new HashMap<>();

    TradeBuilder() {
        super();
    }

    public TradeBuilder tradeId(final String tradeId) {
        this.tradeId = tradeId;
        return this;
    }

    public TradeBuilder parentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    public TradeBuilder units(final int units) {
        this.units = units;
        return this;
    }

    public TradeBuilder risk(final double risk) {
        this.risk = risk;
        return this;
    }

    public TradeBuilder initialRisk(final double initialRisk) {
        this.initialRisk = initialRisk;
        return this;
    }

    public TradeBuilder amountAtRisk(final double amountAtRisk) {
        this.amountAtRisk = amountAtRisk;
        return this;
    }

    public TradeBuilder stop(final double stop) {
        this.stop = stop;
        return this;
    }

    public TradeBuilder target(final double target) {
        this.target = target;
        return this;
    }

    public TradeBuilder trailStop(final double trailStop) {
        this.trailStop = trailStop;
        return this;
    }

    public TradeBuilder opened(final Instant opened) {
        this.opened = opened;
        return this;
    }

    public TradeBuilder openPrice(final double openPrice) {
        this.openPrice = openPrice;
        return this;
    }

    public TradeBuilder timeframe(final TimeFrame timeframe) {
        this.timeframe = timeframe;
        return this;
    }

    public TradeBuilder instrument(final Instrument instrument) {
        this.instrument = instrument;
        return this;
    }

    public TradeBuilder closed(final Instant closed) {
        this.closed = closed;
        return this;
    }

    public TradeBuilder closedPrice(final double closedPrice) {
        this.closedPrice = closedPrice;
        return this;
    }

    public TradeBuilder amountAchieved(final double amountAchieved) {
        this.amountAchieved = amountAchieved;
        return this;
    }

    public TradeBuilder initialStop(final double initialStop) {
        this.initialStop = initialStop;
        return this;
    }

    public TradeBuilder extra(String key, Object value) {
        extras.put(key, value);
        return this;
    }

    public Trade build() {
        return new Trade(tradeId,
                parentId,
                units,
                risk,
                initialRisk,
                amountAtRisk,
                stop,
                target,
                trailStop,
                opened,
                openPrice,
                timeframe,
                instrument,
                closed,
                closedPrice,
                amountAchieved,
                initialStop,
                extras);
    }
}
