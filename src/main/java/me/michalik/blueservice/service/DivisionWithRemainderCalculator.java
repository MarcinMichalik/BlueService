package me.michalik.blueservice.service;

import java.math.BigDecimal;

public interface DivisionWithRemainderCalculator {

    default BigDecimal[] calcReminder(BigDecimal amount){
        return this.calcRemainder(amount, BigDecimal.valueOf(1));
    }

    BigDecimal[] calcRemainder(BigDecimal amount, BigDecimal divisor);

}
