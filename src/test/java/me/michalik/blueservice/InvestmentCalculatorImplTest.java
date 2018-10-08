package me.michalik.blueservice;


import me.michalik.blueservice.domain.Fund;
import me.michalik.blueservice.domain.InvestmentCalculatorResult;
import me.michalik.blueservice.domain.InvestmentStyle;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;

public class InvestmentCalculatorImplTest {

    @Test
    public void calculateStageOneTest(){
        InvestmentCalculator investmentCalculator = new InvestmentCalculatorImpl();
        Set<Fund> funds = new HashSet<>();
        InvestmentCalculatorResult result = investmentCalculator.calculateStageOne(new BigDecimal(10), InvestmentStyle.BALANCED, funds);

        assertNull(result);
    }

}
