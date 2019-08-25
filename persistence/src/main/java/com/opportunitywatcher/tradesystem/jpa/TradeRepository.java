package com.opportunitywatcher.tradesystem.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TradeRepository extends CrudRepository<TradeModel, String> {
    List<TradeModel> findByAccountNumberAndOwningUser(final String accountNumber,
                                                      final String owningUser);

    List<TradeModel> findByOwningUser(final String owningUser);

    List<TradeModel> findByAccountNumberAndOwningUserAndClosedIsNull(final String accountNumber,
                                                                     final String owningUser);
}
