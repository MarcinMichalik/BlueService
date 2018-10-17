package me.michalik.blueservice.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class DivisionWithRemainderCalculatorImplTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void withoutDivisor(){
        DivisionWithRemainderCalculator divisionWithRemainderCalculator = new DivisionWithRemainderCalculatorImpl();
        BigDecimal[] result = divisionWithRemainderCalculator.calcReminder(BigDecimal.valueOf(123.54));

        assertEquals(BigDecimal.valueOf(123).stripTrailingZeros(), result[0].stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(0.54).stripTrailingZeros(), result[1].stripTrailingZeros());
    }

    @Test
    public void withDivisor(){
        DivisionWithRemainderCalculator divisionWithRemainderCalculator = new DivisionWithRemainderCalculatorImpl();
        BigDecimal[] result = divisionWithRemainderCalculator.calcRemainder(BigDecimal.valueOf(12354), BigDecimal.valueOf(100));

        assertEquals(BigDecimal.valueOf(123).stripTrailingZeros(), result[0].stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(54).stripTrailingZeros(), result[1].stripTrailingZeros());
    }

    @Test
    public void withDivisorEqualZero(){
        expectedEx.expect(ArithmeticException.class);
        DivisionWithRemainderCalculator divisionWithRemainderCalculator = new DivisionWithRemainderCalculatorImpl();
        BigDecimal[] result = divisionWithRemainderCalculator.calcRemainder(BigDecimal.valueOf(12354), BigDecimal.valueOf(0));

        assertEquals(BigDecimal.valueOf(123).stripTrailingZeros(), result[0].stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(54).stripTrailingZeros(), result[1].stripTrailingZeros());
    }
}
