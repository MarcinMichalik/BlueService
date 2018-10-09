package me.michalik.blueservice.service;

public class AggressivePercentCalculator extends AbstractPercentCalculator {

    private final static Integer POLISH_PERCENT = 40;
    private final static Integer FOREIGN_PERCENT = 20;
    private final static Integer FINANCIAL_PERCENT = 40;

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
