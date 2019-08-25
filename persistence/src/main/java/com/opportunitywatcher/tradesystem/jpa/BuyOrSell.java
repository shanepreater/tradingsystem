package com.opportunitywatcher.tradesystem.jpa;

public enum BuyOrSell {
    B(true),
    S(false);

    private final boolean buying;

    BuyOrSell(boolean buying) {

        this.buying = buying;
    }

    public boolean isBuying() {
        return buying;
    }
}
