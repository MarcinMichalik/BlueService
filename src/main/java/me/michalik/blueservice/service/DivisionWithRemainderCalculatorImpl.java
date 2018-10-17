package me.michalik.blueservice.service;

import java.math.BigDecimal;

public class DivisionWithRemainderCalculatorImpl implements DivisionWithRemainderCalculator {

    @Override
    public BigDecimal[] calcRemainder(BigDecimal amount, BigDecimal divisor) {
        return amount.divideAndRemainder(divisor);
    }

}
