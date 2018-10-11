package me.michalik.blueservice;

import me.michalik.blueservice.domain.Fund;
import me.michalik.blueservice.domain.InvestmentCalculatorResult;
import me.michalik.blueservice.domain.InvestmentStyle;
import me.michalik.blueservice.service.DefaultDivisionCalculator;
import me.michalik.blueservice.service.DivisionCalculator;
import me.michalik.blueservice.service.PercentCalculator;
import me.michalik.blueservice.service.PercentCalculatorFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InvestmentCalculatorImpl implements InvestmentCalculator {

    @Override
    public List<InvestmentCalculatorResult> calculateStageOne(BigDecimal amountOfInvestment, InvestmentStyle investmentStyle, Set<Fund> funds) {

        if(funds.stream().map(Fund::getType).distinct().count()<3){
            throw new RuntimeException(""); // TODO - wymyśleć message i może stworzyć swój wyjątek
        }

        PercentCalculator percentCalculator = PercentCalculatorFactory.createPercentCalculator(investmentStyle);
        if(percentCalculator==null){
            throw new RuntimeException(""); // TODO - wymyśleć message i może stworzyć swój wyjątek
        }

        DivisionCalculator divisionCalculator = new DefaultDivisionCalculator();

        return funds.stream().map(fund -> {
            BigDecimal percent = percentCalculator.calculatePercent(fund.getType(), funds);
            return new InvestmentCalculatorResult(fund, divisionCalculator.calc(amountOfInvestment, percent), percent);
        }).collect(Collectors.toList());
    }

}
