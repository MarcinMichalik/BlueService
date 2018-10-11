package me.michalik.blueservice;


import me.michalik.blueservice.domain.Fund;
import me.michalik.blueservice.domain.FundType;
import me.michalik.blueservice.domain.InvestmentCalculatorResult;
import me.michalik.blueservice.domain.InvestmentStyle;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class InvestmentCalculatorImplTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void validationTest(){
        expectedEx.expect(RuntimeException.class);
        InvestmentCalculator investmentCalculator = new InvestmentCalculatorImpl();
        Set<Fund> funds = new HashSet<>();
        investmentCalculator.calculateStageOne(new BigDecimal(10), InvestmentStyle.BALANCED, funds);
    }

    @Test
    public void calculateStageOneTest(){
        InvestmentCalculator investmentCalculator = new InvestmentCalculatorImpl();

        Set<Fund> funds = new HashSet<>();
        funds.add(new Fund(1L, "Fundusz Polski 1", FundType.POLISH));
        funds.add(new Fund(2L, "Fundusz Polski 2", FundType.POLISH));
        funds.add(new Fund(3L, "Fundusz Zagraniczny 1", FundType.FOREIGN));
        funds.add(new Fund(4L, "Fundusz Zagraniczny 2", FundType.FOREIGN));
        funds.add(new Fund(5L, "Fundusz Zagraniczny 3", FundType.FOREIGN));
        funds.add(new Fund(6L, "Fundusz Pieniężny 1", FundType.FINANCIAL));

        List<InvestmentCalculatorResult> result = investmentCalculator.calculateStageOne(new BigDecimal(10000), InvestmentStyle.SAFE, funds);

        BigDecimal sumAmount = result.stream().map(InvestmentCalculatorResult::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0));
        BigDecimal sumPercent = result.stream().map(InvestmentCalculatorResult::getPercent).reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0));

        assertAll(
                () -> assertEquals(funds.size(), result.size()),
                () -> assertEquals(0, new BigDecimal(10000).compareTo(sumAmount)),
                () -> assertEquals(0, new BigDecimal(100).compareTo(sumPercent))
        );

    }

    @Test
    public void calculateStageOneVersionTwoTest(){
        InvestmentCalculator investmentCalculator = new InvestmentCalculatorImpl();

        Set<Fund> funds = new HashSet<>();
        funds.add(new Fund(1L, "Fundusz Polski 1", FundType.POLISH));
        funds.add(new Fund(2L, "Fundusz Polski 2", FundType.POLISH));
        funds.add(new Fund(3L, "Fundusz Polski 3", FundType.POLISH));
        funds.add(new Fund(4L, "Fundusz Zagraniczny 2", FundType.FOREIGN));
        funds.add(new Fund(5L, "Fundusz Zagraniczny 3", FundType.FOREIGN));
        funds.add(new Fund(6L, "Fundusz Pieniężny 1", FundType.FINANCIAL));

        List<InvestmentCalculatorResult> result = investmentCalculator.calculateStageOne(new BigDecimal(10000), InvestmentStyle.SAFE, funds);

        for (InvestmentCalculatorResult investmentCalculatorResult : result) {
            System.out.println(investmentCalculatorResult.getFund().getName() + " " + investmentCalculatorResult.getFund().getType() + " " + investmentCalculatorResult.getPercent() + "% " + investmentCalculatorResult.getAmount());
        }

        BigDecimal sumAmount = result.stream().map(InvestmentCalculatorResult::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0));
        BigDecimal sumPercent = result.stream().map(InvestmentCalculatorResult::getPercent).reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0));

        assertAll(
                () -> assertEquals(funds.size(), result.size()),
                () -> assertEquals(new BigDecimal(10000).stripTrailingZeros(), sumAmount.stripTrailingZeros()),
                () -> assertEquals(new BigDecimal(100).stripTrailingZeros(), sumPercent.stripTrailingZeros())
        );

    }
}
