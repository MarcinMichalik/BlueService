package me.michalik.blueservice.service;

import me.michalik.blueservice.domain.Fund;

import java.math.BigDecimal;
import java.util.Set;

public interface InvestmentValidator {

    Boolean validFunds(Set<Fund> funds);
    Boolean validAmount(BigDecimal amount);
}
