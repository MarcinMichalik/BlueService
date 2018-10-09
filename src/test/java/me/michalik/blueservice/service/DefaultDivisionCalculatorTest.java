package me.michalik.blueservice.service;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class DefaultDivisionCalculatorTest {

    @Test
    public void integerCalcTest(){
        DivisionCalculator divisionCalculator = new DefaultDivisionCalculator();
        BigDecimal result = divisionCalculator.calc(BigDecimal.valueOf(10000), BigDecimal.valueOf(10));

        assertEquals(BigDecimal.valueOf(1000).stripTrailingZeros(), result.stripTrailingZeros());
        assertEquals(0, BigDecimal.valueOf(1000).compareTo(result));
    }

    @Test
    public void floatingCalcTest(){
        DivisionCalculator divisionCalculator = new DefaultDivisionCalculator();
        BigDecimal result = divisionCalculator.calc(BigDecimal.valueOf(5), BigDecimal.valueOf(50));

        assertEquals(BigDecimal.valueOf(2.5).stripTrailingZeros(), result.stripTrailingZeros());
        assertEquals(0, BigDecimal.valueOf(2.5).compareTo(result));
    }
}
