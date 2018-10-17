package me.michalik.blueservice.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class InvestmentResult {

    private Fund fund;
    private BigDecimal amount;
    private BigDecimal percent;

    public InvestmentResult(Fund fund, BigDecimal amount, BigDecimal percent) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvestmentResult result = (InvestmentResult) o;
        return Objects.equals(fund, result.fund) &&
                Objects.equals(amount.stripTrailingZeros(), result.amount.stripTrailingZeros()) &&
                Objects.equals(percent.stripTrailingZeros(), result.percent.stripTrailingZeros());
    }

    @Override
    public int hashCode() {
        return Objects.hash(fund, amount.stripTrailingZeros(), percent.stripTrailingZeros());
    }
}
