package me.michalik.blueservice.service;

import me.michalik.blueservice.domain.InvestmentStyle;

public class PercentCalculatorFactory {

    public static PercentCalculator createPercentCalculator(InvestmentStyle investmentStyle){
        switch (investmentStyle){
            case SAFE:
                return new SafePercentCalculator();
            case AGGRESSIVE:
                return new AggressivePercentCalculator();
            case BALANCED:
                return new BalancedPercentCalculator();
            default:
                return null;
        }
    }

}
