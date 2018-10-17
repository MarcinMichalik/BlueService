package me.michalik.blueservice;

import me.michalik.blueservice.domain.Fund;
import me.michalik.blueservice.domain.InvestmentWithInseparableAmountResult;
import me.michalik.blueservice.domain.InvestmentStyle;

import java.math.BigDecimal;
import java.util.Set;

public interface InvestmentWithInseparableAmountCalculator {

    InvestmentWithInseparableAmountResult calculate(BigDecimal amountOfInvestment, InvestmentStyle investmentStyle, Set<Fund> funds);
}
