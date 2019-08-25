package com.opportunitywatcher.tradesystem.jpa;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "trades")
public class TradeModel {
    @Id
    @Column(name = "internal_id")
    private String id;

    @Column(name = "id")
    private String brokerId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "owning_user")
    private String owningUser;

    @Column(name = "parent_id")
    private String parentId;

    private String instrument;

    private Integer timeframe;

    @Column(name = "risk_pips")
    private Double riskPips;

    @Column(name = "risk_amount")
    private Double riskAmount;

    private Integer units;

    private String opened;

    @Column(name = "requested_price")
    private Double requestedPrice;

    @Column(name = "filled_price")
    private Double filledPrice;

    @Column(name = "initial_stop")
    private Double initialStop;

    @Column(name = "initial_risk")
    private Double initialRisk;

    private Double stop;

    @Column(name = "trail_stop")
    private Double trailStop;

    private Double target;

    private String closed;

    @Column(name = "requested_close")
    private Double requestedClose;

    @Column(name = "actual_close")
    private Double actualClose;

    private Double profit;

    @Enumerated(EnumType.STRING)
    private BuyOrSell buy;

    private String extras;
}
