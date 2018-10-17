package me.michalik.blueservice.service;

import me.michalik.blueservice.domain.Fund;
import me.michalik.blueservice.domain.FundType;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvestmentValidatorImplTest {

    @Test
    public void validFundsExpectedTrue(){
        Set<Fund> funds = new HashSet<>();
        funds.add(new Fund(1L, "Fundusz Polski 1", FundType.POLISH));
        funds.add(new Fund(3L, "Fundusz Zagraniczny 1", FundType.FOREIGN));
        funds.add(new Fund(6L, "Fundusz Pieniężny 1", FundType.FINANCIAL));

        InvestmentValidator investmentValidator = new InvestmentValidatorImpl();

        assertEquals(true, investmentValidator.validFunds(funds));

    }

    @Test
    public void validFundsExpectedFalse(){
        Set<Fund> funds = new HashSet<>();
        funds.add(new Fund(1L, "Fundusz Polski 1", FundType.POLISH));
        funds.add(new Fund(3L, "Fundusz Zagraniczny 1", FundType.FOREIGN));

        InvestmentValidator investmentValidator = new InvestmentValidatorImpl();

        assertEquals(false, investmentValidator.validFunds(funds));

    }

    @Test
    public void validAmountExpectedTrue(){
        InvestmentValidator investmentValidator = new InvestmentValidatorImpl();

        assertEquals(true, investmentValidator.validAmount(BigDecimal.valueOf(1)));
    }

    @Test
    public void validAmountExpectedFalse(){
        InvestmentValidator investmentValidator = new InvestmentValidatorImpl();

        assertEquals(false, investmentValidator.validAmount(BigDecimal.valueOf(0)));
    }
}
