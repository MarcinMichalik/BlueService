package me.michalik.blueservice.service;

public class BalancedPercentCalculator extends AbstractPercentCalculator {

    private final static Integer POLISH_PERCENT = 30;
    private final static Integer FOREIGN_PERCENT = 60;
    private final static Integer FINANCIAL_PERCENT = 10;

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
