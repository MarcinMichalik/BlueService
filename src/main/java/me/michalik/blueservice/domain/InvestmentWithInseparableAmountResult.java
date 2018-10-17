package me.michalik.blueservice.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InvestmentWithInseparableAmountResult {

    private List<InvestmentResult> results;
    private BigDecimal inseparableAmount;

    public InvestmentWithInseparableAmountResult() {
        this.results = new ArrayList<>();
        this.inseparableAmount = BigDecimal.valueOf(0);
    }

    public InvestmentWithInseparableAmountResult(List<InvestmentResult> results, BigDecimal inseparableAmount) {
        this.results = results;
        this.inseparableAmount = inseparableAmount;
    }

    public void addInseparableAmount(BigDecimal value){
        this.inseparableAmount = inseparableAmount.add(value);
    }

    public List<InvestmentResult> getResults() {
        return results;
    }

    public void setResults(List<InvestmentResult> results) {
        this.results = results;
    }

    public BigDecimal getInseparableAmount() {
        return inseparableAmount;
    }

    public void setInseparableAmount(BigDecimal inseparableAmount) {
        this.inseparableAmount = inseparableAmount;
    }
}
