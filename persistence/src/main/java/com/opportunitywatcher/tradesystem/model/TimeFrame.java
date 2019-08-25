package com.opportunitywatcher.tradesystem.model;

public enum TimeFrame {
    RENKO("Renko", "Renko", 0),
    M1("1M", "Minute", 1),
    M5("5M", "5 Minute", 5),
    M15("15M", "15 Minute", 15),
    M60("1H", "Hour", 60),
    H4("4H", "4 Hour", 480),
    D("D", "Daily", 1440),
    W("W", "Weekly", 99),
    MON("Mn", "Monthly", 99);

    private final String shortName;
    private final String longName;
    private final int numericVal;

    TimeFrame(String shortName, String longName, int numericVal) {
        this.shortName = shortName;
        this.longName = longName;
        this.numericVal = numericVal;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public int getNumericVal() {
        return numericVal;
    }

    public static final TimeFrame valueOfInteger(final int tf) {
        for(TimeFrame timeFrame: TimeFrame.values()) {
            if(timeFrame.numericVal == tf) {
                return timeFrame;
            }
        }
        throw new IllegalStateException("Unknown timeframe value provided: " + Integer.toString(tf));
    }
}
