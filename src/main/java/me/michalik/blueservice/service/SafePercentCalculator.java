package me.michalik.blueservice.service;

public class SafePercentCalculator extends AbstractPercentCalculator {

    private final static Integer POLISH_PERCENT = 20;
    private final static Integer FOREIGN_PERCENT = 75;
    private final static Integer FINANCIAL_PERCENT = 5;

    @Override
    Integer getPolishPercent() {
        return POLISH_PERCENT;
    }

    @Override
    Integer getForeignPercent() {
        return FOREIGN_PERCENT;
    }

    @Override
    Integer getFinancialPercent() {
        return FINANCIAL_PERCENT;
    }
}
