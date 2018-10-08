package me.michalik.blueservice;

import me.michalik.blueservice.domain.Fund;
import me.michalik.blueservice.domain.InvestmentCalculatorResult;
import me.michalik.blueservice.domain.InvestmentStyle;

import java.math.BigDecimal;
import java.util.Set;

public interface InvestmentCalculator {

    InvestmentCalculatorResult calculateStageOne(BigDecimal amountOfInvestment, InvestmentStyle investmentStyle, Set<Fund> funds);

}
