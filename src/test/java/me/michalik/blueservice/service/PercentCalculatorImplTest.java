package me.michalik.blueservice.service;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class PercentCalculatorImplTest {

    @Test
    public void percent20DivisionBy2(){
        PercentCalculator percentCalculator = new PercentCalculatorImpl();
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(BigDecimal.valueOf(10));
        expected.add(BigDecimal.valueOf(10));
        List<BigDecimal> resultList = percentCalculator.dividePercent(20, 2);

        assertIterableEquals(
                expected.stream().map(BigDecimal::stripTrailingZeros).collect(Collectors.toList()),
                resultList.stream().map(BigDecimal::stripTrailingZeros).collect(Collectors.toList())
        );
    }

    @Test
    public void percent20DivisionBy3(){
        PercentCalculator percentCalculator = new PercentCalculatorImpl();
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(BigDecimal.valueOf(6.68));
        expected.add(BigDecimal.valueOf(6.66));
        expected.add(BigDecimal.valueOf(6.66));
        List<BigDecimal> resultList = percentCalculator.dividePercent(20, 3);

        assertIterableEquals(
                expected.stream().map(BigDecimal::stripTrailingZeros).collect(Collectors.toList()),
                resultList.stream().map(BigDecimal::stripTrailingZeros).collect(Collectors.toList())
        );
    }
}
