package me.michalik.blueservice;

import me.michalik.blueservice.domain.Fund;
import me.michalik.blueservice.domain.InvestmentResult;
import me.michalik.blueservice.domain.InvestmentStyle;
import me.michalik.blueservice.domain.InvestmentWithInseparableAmountResult;
import me.michalik.blueservice.service.DivisionWithRemainderCalculator;
import me.michalik.blueservice.service.DivisionWithRemainderCalculatorImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InvestmentWithInseparableAmountCalculatorImpl implements InvestmentWithInseparableAmountCalculator {

    private InvestmentCalculator investmentCalculator;
    private DivisionWithRemainderCalculator divisionWithRemainderCalculator;

    public InvestmentWithInseparableAmountCalculatorImpl() {
        this.investmentCalculator = new InvestmentCalculatorImpl();
        this.divisionWithRemainderCalculator = new DivisionWithRemainderCalculatorImpl();
    }

    public InvestmentWithInseparableAmountCalculatorImpl(InvestmentCalculator investmentCalculator, DivisionWithRemainderCalculator divisionWithRemainderCalculator) {
        this.investmentCalculator = investmentCalculator;
        this.divisionWithRemainderCalculator = divisionWithRemainderCalculator;
    }

    @Override
    public InvestmentWithInseparableAmountResult calculate(BigDecimal amountOfInvestment, InvestmentStyle investmentStyle, Set<Fund> funds) {

        List<InvestmentResult> investmentResults = investmentCalculator.calculate(amountOfInvestment, investmentStyle, funds);

        InvestmentWithInseparableAmountResult result = new InvestmentWithInseparableAmountResult();
        result.setResults(investmentResults.stream()
                .peek(investmentResult -> {
                    BigDecimal[] remainder = divisionWithRemainderCalculator.calcReminder(investmentResult.getAmount());
                    result.addInseparableAmount(remainder[1]);
                    investmentResult.setAmount(remainder[0]);
                })
                .collect(Collectors.toList()));

        return result;
    }

}
