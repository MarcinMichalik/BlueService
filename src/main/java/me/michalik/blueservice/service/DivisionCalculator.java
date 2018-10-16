package me.michalik.blueservice.service;

import java.math.BigDecimal;

public interface DivisionCalculator {

    BigDecimal calc(BigDecimal amount, BigDecimal percent);
    BigDecimal[] calcStage2(BigDecimal amount, BigDecimal percent);

}
