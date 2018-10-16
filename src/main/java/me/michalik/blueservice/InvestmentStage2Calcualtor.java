package me.michalik.blueservice;

import me.michalik.blueservice.domain.Fund;
import me.michalik.blueservice.domain.InvestmentStage2CalculatorResult;
import me.michalik.blueservice.domain.InvestmentStyle;

import java.math.BigDecimal;
import java.util.Set;

public interface InvestmentStage2Calcualtor {

    InvestmentStage2CalculatorResult calculateStageTwo(BigDecimal amountOfInvestment, InvestmentStyle investmentStyle, Set<Fund> funds);
}
