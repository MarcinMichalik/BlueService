package me.michalik.blueservice.domain;

import java.math.BigDecimal;

public class InvestmentCalculatorResult {

    private Fund fund;
    private BigDecimal amount;
    private BigDecimal percent;

    public InvestmentCalculatorResult(Fund fund, BigDecimal amount, BigDecimal percent) {
        this.fund = fund;
        this.amount = amount;
        this.percent = percent;
    }

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }
}
