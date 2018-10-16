package me.michalik.blueservice.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InvestmentStage2CalculatorResult {

    private List<InvestmentCalculatorResult> results;
    private BigDecimal inseparableAmount;

    public InvestmentStage2CalculatorResult() {
        this.results = new ArrayList<>();
        this.inseparableAmount = BigDecimal.valueOf(0);
    }

    public InvestmentStage2CalculatorResult(List<InvestmentCalculatorResult> results, BigDecimal inseparableAmount) {
        this.results = results;
        this.inseparableAmount = inseparableAmount;
    }

    public List<InvestmentCalculatorResult> getResults() {
        return results;
    }

    public void setResults(List<InvestmentCalculatorResult> results) {
        this.results = results;
    }

    public BigDecimal getInseparableAmount() {
        return inseparableAmount;
    }

    public void setInseparableAmount(BigDecimal inseparableAmount) {
        this.inseparableAmount = inseparableAmount;
    }
}
