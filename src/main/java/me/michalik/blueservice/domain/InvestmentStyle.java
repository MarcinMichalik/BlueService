package me.michalik.blueservice.domain;

import java.math.BigDecimal;
import java.util.Collection;

public enum InvestmentStyle {
    // Bezpieczny
    SAFE(20, 75, 5),
    // Zrównoważony
    BALANCED(30, 60, 10),
    // Agresywny
    AGGRESSIVE(40, 20, 40);

    private int polishPercent;
    private int foreignPercent;
    private int financialPercent;

    InvestmentStyle(int polishPercent, int foreignPercent, int financialPercent) {
        this.polishPercent = polishPercent;
        this.foreignPercent = foreignPercent;
        this.financialPercent = financialPercent;
    }

    public int getPolishPercent() {
        return polishPercent;
    }

    public int getForeignPercent() {
        return foreignPercent;
    }

    public int getFinancialPercent() {
        return financialPercent;
    }

    public BigDecimal calculatePercent(FundType fundType, Collection<Fund> funds) {
        switch (fundType){
            case POLISH:
                return new BigDecimal(getPolishPercent()).divide(BigDecimal.valueOf(getNumberOfPolishFunds(funds)));
            case FOREIGN:
                return new BigDecimal(getForeignPercent()).divide(BigDecimal.valueOf(getNumberOfForeignFunds(funds)));
            case FINANCIAL:
                return new BigDecimal(getFinancialPercent()).divide(BigDecimal.valueOf(getNumberOfFinancialFunds(funds)));
            default:
                return BigDecimal.valueOf(0);
        }
    }

    private long getNumberOfPolishFunds(Collection<Fund> funds){
        return funds.stream().filter(fund -> fund.getType().equals(FundType.POLISH)).count();
    }

    private long getNumberOfForeignFunds(Collection<Fund> funds){
        return funds.stream().filter(fund -> fund.getType().equals(FundType.FOREIGN)).count();
    }

    private long getNumberOfFinancialFunds(Collection<Fund> funds){
        return funds.stream().filter(fund -> fund.getType().equals(FundType.FINANCIAL)).count();
    }
}
