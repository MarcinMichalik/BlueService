package me.michalik.blueservice;

import me.michalik.blueservice.domain.Fund;
import me.michalik.blueservice.domain.FundType;
import me.michalik.blueservice.domain.InvestmentResult;
import me.michalik.blueservice.domain.InvestmentStyle;
import me.michalik.blueservice.exceptions.InsufficientAmountOfInvestmentException;
import me.michalik.blueservice.exceptions.MissingFundTypeException;
import me.michalik.blueservice.service.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InvestmentCalculatorImpl implements InvestmentCalculator {

    private PercentCalculator percentCalculator;
    private DivisionCalculator divisionCalculator;
    private InvestmentValidator investmentValidator;

    public InvestmentCalculatorImpl() {
        this.percentCalculator = new PercentCalculatorImpl();
        this.divisionCalculator = new DivisionCalculatorImpl();
        this.investmentValidator = new InvestmentValidatorImpl();
    }

    public InvestmentCalculatorImpl(PercentCalculator percentCalculator, DivisionCalculator divisionCalculator, InvestmentValidator investmentValidator) {
        this.percentCalculator = percentCalculator;
        this.divisionCalculator = divisionCalculator;
        this.investmentValidator = investmentValidator;
    }

    @Override
    public List<InvestmentResult> calculate(BigDecimal amountOfInvestment, InvestmentStyle investmentStyle, Set<Fund> funds) {

        if(!investmentValidator.validFunds(funds)){
            throw new MissingFundTypeException("Not found all fund types");
        }

        if(!investmentValidator.validAmount(amountOfInvestment)){
            throw new InsufficientAmountOfInvestmentException("Amount of investment is too small");
        }

        // POLISH
        List<Fund> polishFunds = getFundByType(funds, FundType.POLISH);
        List<BigDecimal> polishPercent = percentCalculator.dividePercent(investmentStyle.getPolishPercent(), polishFunds.size());
        List<InvestmentResult> investmentCalculatorResults = mapToInvestmentCalculatorResult(polishFunds, polishPercent, amountOfInvestment);

        // FOREIGN
        List<Fund> foreignFunds = getFundByType(funds, FundType.FOREIGN);
        List<BigDecimal> foreignPercent = percentCalculator.dividePercent(investmentStyle.getForeignPercent(), foreignFunds.size());
        investmentCalculatorResults.addAll(mapToInvestmentCalculatorResult(foreignFunds, foreignPercent, amountOfInvestment));

        // FINANCIAL
        List<Fund> financialFunds = getFundByType(funds, FundType.FINANCIAL);
        List<BigDecimal> financialPercent = percentCalculator.dividePercent(investmentStyle.getFinancialPercent(), financialFunds.size());
        investmentCalculatorResults.addAll(mapToInvestmentCalculatorResult(financialFunds, financialPercent, amountOfInvestment));

        return investmentCalculatorResults;
    }

    private List<InvestmentResult> mapToInvestmentCalculatorResult(List<Fund> funds, List<BigDecimal> percents, BigDecimal amountOfInvestment){
        return funds.stream().map(fund -> {
            BigDecimal percent = percents.remove(0);
            return new InvestmentResult(fund, divisionCalculator.calc(amountOfInvestment, percent), percent);
        }).collect(Collectors.toList());
    }

    private List<Fund> getFundByType(Collection<Fund> funds, FundType fundType){
        return funds.stream().filter(fund -> fund.getType().equals(fundType)).collect(Collectors.toList());
    }
}
