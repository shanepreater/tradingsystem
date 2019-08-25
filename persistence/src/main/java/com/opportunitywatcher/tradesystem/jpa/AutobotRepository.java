package com.opportunitywatcher.tradesystem.jpa;

import com.opportunitywatcher.tradesystem.model.Autobot;
import com.opportunitywatcher.tradesystem.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AutobotRepository extends CrudRepository<Autobot, String> {
    Optional<Autobot> findByAccountNumberAndUser(final String accountNumber, final User user);
}
