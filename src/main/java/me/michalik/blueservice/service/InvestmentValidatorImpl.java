package me.michalik.blueservice.service;

import me.michalik.blueservice.domain.Fund;

import java.math.BigDecimal;
import java.util.Set;

public class InvestmentValidatorImpl implements InvestmentValidator {

    @Override
    public Boolean validFunds(Set<Fund> funds) {
        return funds.stream().map(Fund::getType).distinct().count()>=3;
    }

    @Override
    public Boolean validAmount(BigDecimal amount) {
        return amount.compareTo(BigDecimal.valueOf(0))>0;
    }
}
