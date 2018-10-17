package me.michalik.blueservice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DivisionCalculatorImpl implements DivisionCalculator {

    @Override
    public BigDecimal calc(BigDecimal amount, BigDecimal percent) {
        return amount.multiply(percent).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_EVEN);
    }


}
