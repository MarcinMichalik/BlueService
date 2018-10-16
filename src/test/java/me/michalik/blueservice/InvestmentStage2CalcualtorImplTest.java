package me.michalik.blueservice;

import me.michalik.blueservice.domain.Fund;
import me.michalik.blueservice.domain.FundType;
import me.michalik.blueservice.domain.InvestmentStage2CalculatorResult;
import me.michalik.blueservice.domain.InvestmentStyle;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvestmentStage2CalcualtorImplTest {

    @Test
    public void simpleTest(){
        Set<Fund> funds = new HashSet<>();
        funds.add(new Fund(1L, "Fundusz Polski 1", FundType.POLISH));
        funds.add(new Fund(2L, "Fundusz Polski 2", FundType.POLISH));
        funds.add(new Fund(3L, "Fundusz Zagraniczny 1", FundType.FOREIGN));
        funds.add(new Fund(4L, "Fundusz Zagraniczny 2", FundType.FOREIGN));
        funds.add(new Fund(5L, "Fundusz Zagraniczny 3", FundType.FOREIGN));
        funds.add(new Fund(6L, "Fundusz Pieniężny 1", FundType.FINANCIAL));

        InvestmentStage2Calcualtor investmentCalculator = new InvestmentStage2CalcualtorImpl();
        InvestmentStage2CalculatorResult result = investmentCalculator.calculateStageTwo(new BigDecimal(10005), InvestmentStyle.SAFE, funds);

        assertEquals(BigDecimal.valueOf(2), result.getInseparableAmount().stripTrailingZeros());
    }

    @Test
    public void simpleSecondTest(){
        Set<Fund> funds = new HashSet<>();
        funds.add(new Fund(1L, "Fundusz Polski 1", FundType.POLISH));
        funds.add(new Fund(2L, "Fundusz Polski 2", FundType.POLISH));
        funds.add(new Fund(3L, "Fundusz Zagraniczny 1", FundType.FOREIGN));
        funds.add(new Fund(4L, "Fundusz Zagraniczny 2", FundType.FOREIGN));
        funds.add(new Fund(5L, "Fundusz Zagraniczny 3", FundType.FOREIGN));
        funds.add(new Fund(6L, "Fundusz Pieniężny 1", FundType.FINANCIAL));

        InvestmentStage2Calcualtor investmentCalculator = new InvestmentStage2CalcualtorImpl();
        InvestmentStage2CalculatorResult result = investmentCalculator.calculateStageTwo(new BigDecimal(10001), InvestmentStyle.SAFE, funds);

        assertEquals(BigDecimal.valueOf(1), result.getInseparableAmount().stripTrailingZeros());
    }
}
