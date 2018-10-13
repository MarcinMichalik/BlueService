package me.michalik.blueservice;


import me.michalik.blueservice.domain.Fund;
import me.michalik.blueservice.domain.FundType;
import me.michalik.blueservice.domain.InvestmentCalculatorResult;
import me.michalik.blueservice.domain.InvestmentStyle;
import me.michalik.blueservice.exceptions.MissingFundTypeException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class InvestmentCalculatorImplTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void getFundByType() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        List<Fund> expected = new ArrayList<>();
        expected.add(new Fund(1L, "Fundusz Polski 1", FundType.POLISH));
        expected.add(new Fund(2L, "Fundusz Polski 2", FundType.POLISH));

        Set<Fund> funds = new HashSet<>();
        funds.add(new Fund(1L, "Fundusz Polski 1", FundType.POLISH));
        funds.add(new Fund(2L, "Fundusz Polski 2", FundType.POLISH));
        funds.add(new Fund(3L, "Fundusz Zagraniczny 1", FundType.FOREIGN));
        funds.add(new Fund(4L, "Fundusz Zagraniczny 2", FundType.FOREIGN));
        funds.add(new Fund(5L, "Fundusz Zagraniczny 3", FundType.FOREIGN));
        funds.add(new Fund(6L, "Fundusz Pieniężny 1", FundType.FINANCIAL));


        Object[] methodObjects = {funds, FundType.POLISH};
        Class<?>[] methodClasses = {Collection.class, FundType.class};
        Class targetClass = Class.forName("me.michalik.blueservice.InvestmentCalculatorImpl");
        Method method = targetClass.getDeclaredMethod("getFundByType", methodClasses);
        method.setAccessible(true);
        List<Fund> result = (List<Fund>) method.invoke(targetClass.newInstance(), methodObjects);

        assertAll(
                () -> assertThat(result, containsInAnyOrder(expected.toArray())),
                () -> assertEquals(expected.size(), result.size())
        );
    }

    @Test
    public void mapToInvestmentCalculatorResultTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        List<InvestmentCalculatorResult> expected = new ArrayList<>();
        expected.add(new InvestmentCalculatorResult(new Fund(1L, "Fundusz Polski 1", FundType.POLISH), BigDecimal.valueOf(1000), BigDecimal.valueOf(10)));
        expected.add(new InvestmentCalculatorResult(new Fund(2L, "Fundusz Polski 2", FundType.POLISH), BigDecimal.valueOf(1000), BigDecimal.valueOf(10)));


        List<Fund> funds = new ArrayList<>();
        funds.add(new Fund(1L, "Fundusz Polski 1", FundType.POLISH));
        funds.add(new Fund(2L, "Fundusz Polski 2", FundType.POLISH));

        List<BigDecimal> percents = new ArrayList<>();
        percents.add(BigDecimal.valueOf(10));
        percents.add(BigDecimal.valueOf(10));

        Object[] methodObjects = {funds, percents, BigDecimal.valueOf(10000)};
        Class<?>[] methodClasses = {List.class, List.class, BigDecimal.class};
        Class targetClass = Class.forName("me.michalik.blueservice.InvestmentCalculatorImpl");
        Method method = targetClass.getDeclaredMethod("mapToInvestmentCalculatorResult", methodClasses);
        method.setAccessible(true);
        List<InvestmentCalculatorResult> result = (List<InvestmentCalculatorResult>) method.invoke(targetClass.newInstance(), methodObjects);

        assertThat(result, containsInAnyOrder(expected.toArray()));
    }

    @Test
    public void validationTest(){
        expectedEx.expect(MissingFundTypeException.class);
        InvestmentCalculator investmentCalculator = new InvestmentCalculatorImpl();
        Set<Fund> funds = new HashSet<>();
        investmentCalculator.calculateStageOne(new BigDecimal(10), InvestmentStyle.BALANCED, funds);
    }

    @Test
    public void calculateStageOneTest(){
        List<InvestmentCalculatorResult> expected = new ArrayList<>();
        expected.add(new InvestmentCalculatorResult(new Fund(1L, "Fundusz Polski 1", FundType.POLISH), BigDecimal.valueOf(1000), BigDecimal.valueOf(10)));
        expected.add(new InvestmentCalculatorResult(new Fund(2L, "Fundusz Polski 2", FundType.POLISH), BigDecimal.valueOf(1000), BigDecimal.valueOf(10)));
        expected.add(new InvestmentCalculatorResult(new Fund(3L, "Fundusz Zagraniczny 1", FundType.FOREIGN), BigDecimal.valueOf(2500), BigDecimal.valueOf(25)));
        expected.add(new InvestmentCalculatorResult(new Fund(4L, "Fundusz Zagraniczny 2", FundType.FOREIGN), BigDecimal.valueOf(2500), BigDecimal.valueOf(25)));
        expected.add(new InvestmentCalculatorResult(new Fund(5L, "Fundusz Zagraniczny 3", FundType.FOREIGN), BigDecimal.valueOf(2500), BigDecimal.valueOf(25)));
        expected.add(new InvestmentCalculatorResult(new Fund(6L, "Fundusz Pieniężny 1", FundType.FINANCIAL), BigDecimal.valueOf(500), BigDecimal.valueOf(5)));


        Set<Fund> funds = new HashSet<>();
        funds.add(new Fund(1L, "Fundusz Polski 1", FundType.POLISH));
        funds.add(new Fund(2L, "Fundusz Polski 2", FundType.POLISH));
        funds.add(new Fund(3L, "Fundusz Zagraniczny 1", FundType.FOREIGN));
        funds.add(new Fund(4L, "Fundusz Zagraniczny 2", FundType.FOREIGN));
        funds.add(new Fund(5L, "Fundusz Zagraniczny 3", FundType.FOREIGN));
        funds.add(new Fund(6L, "Fundusz Pieniężny 1", FundType.FINANCIAL));

        InvestmentCalculator investmentCalculator = new InvestmentCalculatorImpl();
        List<InvestmentCalculatorResult> result = investmentCalculator.calculateStageOne(new BigDecimal(10000), InvestmentStyle.SAFE, funds);

        BigDecimal sumAmount = result.stream().map(InvestmentCalculatorResult::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0));
        BigDecimal sumPercent = result.stream().map(InvestmentCalculatorResult::getPercent).reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0));

        assertAll(
                () -> assertEquals(funds.size(), result.size()),
                () -> assertEquals(0, new BigDecimal(10000).compareTo(sumAmount)),
                () -> assertEquals(0, new BigDecimal(100).compareTo(sumPercent)),
                () -> assertThat(result, containsInAnyOrder(expected.toArray()))
        );

    }

    @Test
    public void calculateStageOneVersionTwoTest(){
        List<InvestmentCalculatorResult> expected = new ArrayList<>();
        expected.add(new InvestmentCalculatorResult(new Fund(1L, "Fundusz Polski 1", FundType.POLISH), BigDecimal.valueOf(668), BigDecimal.valueOf(6.68)));
        expected.add(new InvestmentCalculatorResult(new Fund(2L, "Fundusz Polski 2", FundType.POLISH), BigDecimal.valueOf(666), BigDecimal.valueOf(6.66)));
        expected.add(new InvestmentCalculatorResult(new Fund(3L, "Fundusz Polski 3", FundType.POLISH), BigDecimal.valueOf(666), BigDecimal.valueOf(6.66)));
        expected.add(new InvestmentCalculatorResult(new Fund(4L, "Fundusz Zagraniczny 1", FundType.FOREIGN), BigDecimal.valueOf(3750), BigDecimal.valueOf(37.5)));
        expected.add(new InvestmentCalculatorResult(new Fund(5L, "Fundusz Zagraniczny 2", FundType.FOREIGN), BigDecimal.valueOf(3750), BigDecimal.valueOf(37.5)));
        expected.add(new InvestmentCalculatorResult(new Fund(6L, "Fundusz Pieniężny 1", FundType.FINANCIAL), BigDecimal.valueOf(500), BigDecimal.valueOf(5)));



        Set<Fund> funds = new HashSet<>();
        funds.add(new Fund(1L, "Fundusz Polski 1", FundType.POLISH));
        funds.add(new Fund(2L, "Fundusz Polski 2", FundType.POLISH));
        funds.add(new Fund(3L, "Fundusz Polski 3", FundType.POLISH));
        funds.add(new Fund(4L, "Fundusz Zagraniczny 1", FundType.FOREIGN));
        funds.add(new Fund(5L, "Fundusz Zagraniczny 2", FundType.FOREIGN));
        funds.add(new Fund(6L, "Fundusz Pieniężny 1", FundType.FINANCIAL));

        InvestmentCalculator investmentCalculator = new InvestmentCalculatorImpl();
        List<InvestmentCalculatorResult> result = investmentCalculator.calculateStageOne(new BigDecimal(10000), InvestmentStyle.SAFE, funds);

        BigDecimal sumAmount = result.stream().map(InvestmentCalculatorResult::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0));
        BigDecimal sumPercent = result.stream().map(InvestmentCalculatorResult::getPercent).reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0));

        assertAll(
                () -> assertEquals(funds.size(), result.size()),
                () -> assertEquals(new BigDecimal(10000).stripTrailingZeros(), sumAmount.stripTrailingZeros()),
                () -> assertEquals(new BigDecimal(100).stripTrailingZeros(), sumPercent.stripTrailingZeros()),
                () -> assertThat(result, containsInAnyOrder(expected.toArray()))
        );

    }
}
