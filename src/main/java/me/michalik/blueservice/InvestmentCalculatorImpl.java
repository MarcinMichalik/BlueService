package me.michalik.blueservice;

import me.michalik.blueservice.domain.Fund;
import me.michalik.blueservice.domain.FundType;
import me.michalik.blueservice.domain.InvestmentCalculatorResult;
import me.michalik.blueservice.domain.InvestmentStyle;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InvestmentCalculatorImpl implements InvestmentCalculator {

    @Override
    public List<InvestmentCalculatorResult> calculateStageOne(BigDecimal amountOfInvestment, InvestmentStyle investmentStyle, Set<Fund> funds) {
        // TODO - walidacja przekazanych funduszy, według obecnych wymagań wystarczy sprawdzić czy każdy z funduszy występuje przynjamniej raz

        /*
        * 1. Określenie procentu dla każdego funduszu
        *   a) Określić ilość występowania danego rodzaju funduszu
        *   b) W zależności o stylu inwestowania wyliczyć procent dla każdego funduszu
        * 2. Przeliczenie kwoty dla każdego funduszu
        *   a) Na podstawie wyliczonego procentu, wykonać obliczenia na kwocie inwestycji
        *
        * */


        long numberOfPolishFunds = funds.stream().filter(fund -> fund.getType().equals(FundType.POLISH)).count();
        long numberOfForeignFunds = funds.stream().filter(fund -> fund.getType().equals(FundType.FOREIGN)).count();
        long numberOfFinancialFunds = funds.stream().filter(fund -> fund.getType().equals(FundType.FINANCIAL)).count();



        return funds.stream().map(fund -> {
            // W zależności od stylu inwestowania określić procent - wyrzucić sobie to w inną część (może jakaś Fabryka) - zakładamy możliwą zmiannę logiki
            // Wyliczyć kwotę dla wybranego funduszu - wyrzuć sobie do innej części - zakładamy zmianę logiki wyliczania
            return new InvestmentCalculatorResult(fund, new BigDecimal(0), new BigDecimal(0));
        }).collect(Collectors.toList());
        // TODO - walidacja przekazanych funduszy może również odbywać się tutaj poprzez sumowanie wszystkich procentów i sprawdzenie czy równają się 100%

    }

}
