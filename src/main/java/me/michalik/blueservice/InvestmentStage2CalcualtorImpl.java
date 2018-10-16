package me.michalik.blueservice;

import me.michalik.blueservice.domain.*;
import me.michalik.blueservice.exceptions.MissingFundTypeException;
import me.michalik.blueservice.service.DivisionCalculator;
import me.michalik.blueservice.service.DivisionCalculatorImpl;
import me.michalik.blueservice.service.PercentCalculator;
import me.michalik.blueservice.service.PercentCalculatorImpl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InvestmentStage2CalcualtorImpl implements InvestmentStage2Calcualtor {

    @Override
    public InvestmentStage2CalculatorResult calculateStageTwo(BigDecimal amountOfInvestment, InvestmentStyle investmentStyle, Set<Fund> funds) {
        if(funds.stream().map(Fund::getType).distinct().count()<3){
            throw new MissingFundTypeException("Not found all fund types");
        }

        InvestmentStage2CalculatorResult result = new InvestmentStage2CalculatorResult();
        PercentCalculator percentCalculator = new PercentCalculatorImpl();

        // POLISH
        List<Fund> polishFunds = getFundByType(funds, FundType.POLISH);
        List<BigDecimal> polishPercent = percentCalculator.dividePercent(investmentStyle.getPolishPercent(), polishFunds.size());
        List<InvestmentCalculatorResult> investmentCalculatorResults = mapToInvestmentCalculatorResult(polishFunds, polishPercent, amountOfInvestment, result);

        // FOREIGN
        List<Fund> foreignFunds = getFundByType(funds, FundType.FOREIGN);
        List<BigDecimal> foreignPercent = percentCalculator.dividePercent(investmentStyle.getForeignPercent(), foreignFunds.size());
        investmentCalculatorResults.addAll(mapToInvestmentCalculatorResult(foreignFunds, foreignPercent, amountOfInvestment, result));

        // FINANCIAL
        List<Fund> financialFunds = getFundByType(funds, FundType.FINANCIAL);
        List<BigDecimal> financialPercent = percentCalculator.dividePercent(investmentStyle.getFinancialPercent(), financialFunds.size());
        investmentCalculatorResults.addAll(mapToInvestmentCalculatorResult(financialFunds, financialPercent, amountOfInvestment, result));

        result.setResults(investmentCalculatorResults);// TODO - zmienić

        return result;
    }

    private List<InvestmentCalculatorResult> mapToInvestmentCalculatorResult(List<Fund> funds, List<BigDecimal> percents, BigDecimal amountOfInvestment, InvestmentStage2CalculatorResult result){
        DivisionCalculator divisionCalculator = new DivisionCalculatorImpl();

        return funds.stream().map(fund -> {
            BigDecimal percent = percents.remove(0);
            BigDecimal[] amountWithReminder = divisionCalculator.calcStage2(amountOfInvestment, percent);
            result.setInseparableAmount(result.getInseparableAmount().add(amountWithReminder[1]));
            return new InvestmentCalculatorResult(fund, amountWithReminder[0], percent);
        }).collect(Collectors.toList());
    }

    private List<Fund> getFundByType(Collection<Fund> funds, FundType fundType){
        return funds.stream().filter(fund -> fund.getType().equals(fundType)).collect(Collectors.toList());
    }

}
