package me.michalik.blueservice;


import me.michalik.blueservice.domain.Fund;
import me.michalik.blueservice.domain.FundType;
import me.michalik.blueservice.domain.InvestmentCalculatorResult;
import me.michalik.blueservice.domain.InvestmentStyle;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class InvestmentCalculatorImplTest {

    @Test
    public void calculateStageOneEmptyTest(){
        InvestmentCalculator investmentCalculator = new InvestmentCalculatorImpl();
        Set<Fund> funds = new HashSet<>();
        List<InvestmentCalculatorResult> result = investmentCalculator.calculateStageOne(new BigDecimal(10), InvestmentStyle.BALANCED, funds);

        assertEquals(0, result.size());
    }

    @Test
    public void calculateStageOneNotEmptyTest(){
        InvestmentCalculator investmentCalculator = new InvestmentCalculatorImpl();

        Set<Fund> funds = new HashSet<>();
        funds.add(new Fund(1L, "Fundusz Polski 1", FundType.POLISH));
        funds.add(new Fund(2L, "Fundusz Polski 2", FundType.POLISH));
        funds.add(new Fund(3L, "Fundusz Zagraniczny 1", FundType.FOREIGN));
        funds.add(new Fund(4L, "Fundusz Zagraniczny 2", FundType.FOREIGN));
        funds.add(new Fund(5L, "Fundusz Zagraniczny 3", FundType.FOREIGN));
        funds.add(new Fund(6L, "Fundusz Pieniężny 1", FundType.FINANCIAL));

        List<InvestmentCalculatorResult> result = investmentCalculator.calculateStageOne(new BigDecimal(10000), InvestmentStyle.SAFE, funds);

        assertEquals(funds.size(), result.size());
    }
}