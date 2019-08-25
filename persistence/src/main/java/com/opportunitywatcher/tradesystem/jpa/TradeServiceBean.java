package com.opportunitywatcher.tradesystem.jpa;

import com.opportunitywatcher.tradesystem.TradeService;
import com.opportunitywatcher.tradesystem.model.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TradeServiceBean implements TradeService {

    private final TradeRepository repository;

    private final AutobotRepository autobotRepository;

    private final UserRepository userRepository;

    public TradeServiceBean(TradeRepository repository, AutobotRepository autobotRepository, UserRepository userRepository) {
        this.repository = repository;
        this.autobotRepository = autobotRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Trade> findTradesForAccount(String accountId, String owningUser) {
        return repository
                .findByAccountNumberAndOwningUser(accountId, owningUser)
                .stream()
                .map((tm) -> convertToTrade(tm))
                .collect(Collectors.toList());
    }

    @Override
    public List<Trade> findOpenTradesForAccount(String accountId, String owningUser) {

        return repository
                .findByAccountNumberAndOwningUserAndClosedIsNull(accountId, owningUser)
                .stream()
                .map((tm) -> convertToTrade(tm))
                .collect(Collectors.toList());
    }

    @Override
    public List<Trade> finalAllTradesForUser(String user) {

        return repository
                .findByOwningUser(user)
                .stream()
                .map((tm) -> convertToTrade(tm))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Autobot> findConfig(String accountId, String userId) {
        final Optional<Autobot> optionalAutobot = userRepository.findById(userId)
                .map(user -> autobotRepository.findByAccountNumberAndUser(accountId, user).get());

        return optionalAutobot;
    }

    @Override
    public Autobot saveConfig(Autobot config, String userId) {
        return null;
    }

    private Trade convertToTrade(TradeModel tm) {
        final Instrument intrument = Instrument.valueOf(tm.getInstrument().toUpperCase());
        final TimeFrame timeframe = TimeFrame.valueOfInteger(tm.getTimeframe());

        final Instant opened = Instant.parse(tm.getOpened());
        final TradeBuilder tradeBuilder = Trade.builder()
                .tradeId(tm.getId())
                .initialRisk(tm.getInitialRisk())
                .amountAtRisk(tm.getRiskAmount())
                .initialStop(tm.getInitialStop())
                .instrument(intrument)
                .timeframe(timeframe)
                .opened(opened)
                .openPrice(tm.getFilledPrice())
                .units(tm.getUnits())
                .risk(tm.getRiskPips());

        if (tm.getTrailStop() != null) {
            tradeBuilder.trailStop(tm.getTrailStop());
        }

        if (tm.getTarget() != null) {
            tradeBuilder.target(tm.getTarget());
        }

        if (tm.getParentId() != null) {
            tradeBuilder.parentId(tm.getParentId());
        }

        if (tm.getClosed() != null) {
            tradeBuilder.closed(Instant.parse(tm.getClosed()))
                    .closedPrice(tm.getActualClose())
                    .amountAchieved(tm.getProfit());
        }
        return tradeBuilder
                .build();
    }
}
