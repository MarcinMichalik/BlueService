package me.michalik.blueservice.service;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class DivisionWithRemainderCalculatorImplTest {

    @Test
    public void defaultTest(){
        DivisionWithRemainderCalculator divisionWithRemainderCalculator = new DivisionWithRemainderCalculatorImpl();
        BigDecimal[] result = divisionWithRemainderCalculator.calcReminder(BigDecimal.valueOf(123.54));

        assertEquals(BigDecimal.valueOf(123).stripTrailingZeros(), result[0].stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(0.54).stripTrailingZeros(), result[1].stripTrailingZeros());
    }

    @Test
    public void mainTest(){
        DivisionWithRemainderCalculator divisionWithRemainderCalculator = new DivisionWithRemainderCalculatorImpl();
        BigDecimal[] result = divisionWithRemainderCalculator.calcRemainder(BigDecimal.valueOf(12354), BigDecimal.valueOf(100));

        assertEquals(BigDecimal.valueOf(123).stripTrailingZeros(), result[0].stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(54).stripTrailingZeros(), result[1].stripTrailingZeros());
    }
}
