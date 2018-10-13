package me.michalik.blueservice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PercentCalculatorImpl implements PercentCalculator {

    @Override
    public List<BigDecimal> dividePercent(BigDecimal percent, BigDecimal numberOfFunds) {
        BigDecimal onePercent = percent.divide(numberOfFunds, 2, BigDecimal.ROUND_DOWN);
        List<BigDecimal> result = new ArrayList<>(Collections.nCopies(numberOfFunds.intValue(), onePercent));

        BigDecimal sum = result.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        if(!sum.stripTrailingZeros().equals(percent.stripTrailingZeros())){
            result.set(0, result.get(0).add(percent.subtract(sum)));
        }

        return result;
    }

}
