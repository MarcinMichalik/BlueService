package me.michalik.blueservice.service;

import me.michalik.blueservice.domain.Fund;
import me.michalik.blueservice.domain.FundType;

import java.math.BigDecimal;
import java.util.Collection;

public abstract class AbstractPercentCalculator implements PercentCalculator{

    abstract Integer getPolishPercent();
    abstract Integer getForeignPercent();
    abstract Integer getFinancialPercent();

    @Override
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
