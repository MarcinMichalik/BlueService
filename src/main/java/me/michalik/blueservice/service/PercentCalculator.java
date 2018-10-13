package me.michalik.blueservice.service;

import java.math.BigDecimal;
import java.util.List;

public interface PercentCalculator {

    default List<BigDecimal> dividePercent(Integer percent, Integer numberOfFunds){
        return this.dividePercent(BigDecimal.valueOf(percent), BigDecimal.valueOf(numberOfFunds));
    }

    List<BigDecimal> dividePercent(BigDecimal percent, BigDecimal numberOfFunds);
}
