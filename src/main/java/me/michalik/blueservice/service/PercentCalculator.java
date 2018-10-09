package me.michalik.blueservice.service;

import me.michalik.blueservice.domain.Fund;
import me.michalik.blueservice.domain.FundType;

import java.math.BigDecimal;
import java.util.Collection;

public interface PercentCalculator {

    BigDecimal calculatePercent(FundType fundType, Collection<Fund> funds);

}
