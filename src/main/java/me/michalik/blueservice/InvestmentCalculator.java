package me.michalik.blueservice;

import me.michalik.blueservice.domain.Fund;
import me.michalik.blueservice.domain.InvestmentResult;
import me.michalik.blueservice.domain.InvestmentStyle;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface InvestmentCalculator {

    List<InvestmentResult> calculate(BigDecimal amountOfInvestment, InvestmentStyle investmentStyle, Set<Fund> funds);

}
