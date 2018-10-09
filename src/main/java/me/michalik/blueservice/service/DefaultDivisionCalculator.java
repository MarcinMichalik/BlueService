package me.michalik.blueservice.service;

import java.math.BigDecimal;

public class DefaultDivisionCalculator implements DivisionCalculator {

    @Override
    public BigDecimal calc(BigDecimal amount, BigDecimal percent) {
        return amount.multiply(percent.divide(BigDecimal.valueOf(100)));
    }

}
