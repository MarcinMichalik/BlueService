package me.michalik.blueservice.service;

import me.michalik.blueservice.domain.Fund;
import me.michalik.blueservice.domain.FundType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
public class SafePercentCalculatorTest {

    @Test
    public void polishFundTest(){
        PercentCalculator percentCalculator = new SafePercentCalculator();
        List<Fund> funds = new ArrayList<>();
        funds.add(new Fund(1L, "Fundusz Polski 1", FundType.POLISH));
        funds.add(new Fund(2L, "Fundusz Polski 2", FundType.POLISH));
        funds.add(new Fund(3L, "Fundusz Zagraniczny 1", FundType.FOREIGN));
        funds.add(new Fund(4L, "Fundusz Zagraniczny 2", FundType.FOREIGN));
        funds.add(new Fund(5L, "Fundusz Zagraniczny 3", FundType.FOREIGN));
        funds.add(new Fund(6L, "Fundusz Pieniężny 1", FundType.FINANCIAL));

        BigDecimal result = percentCalculator.calculatePercent(FundType.POLISH, funds);

        assertEquals(new BigDecimal(10), result);
    }

    @Test
    public void foreignFundTest(){
        PercentCalculator percentCalculator = new SafePercentCalculator();
        List<Fund> funds = new ArrayList<>();
        funds.add(new Fund(1L, "Fundusz Polski 1", FundType.POLISH));
        funds.add(new Fund(2L, "Fundusz Polski 2", FundType.POLISH));
        funds.add(new Fund(3L, "Fundusz Zagraniczny 1", FundType.FOREIGN));
        funds.add(new Fund(4L, "Fundusz Zagraniczny 2", FundType.FOREIGN));
        funds.add(new Fund(5L, "Fundusz Zagraniczny 3", FundType.FOREIGN));
        funds.add(new Fund(6L, "Fundusz Pieniężny 1", FundType.FINANCIAL));

        BigDecimal result = percentCalculator.calculatePercent(FundType.FOREIGN, funds);

        assertEquals(new BigDecimal(25), result);
    }

    @Test
    public void financialFundTest(){
        PercentCalculator percentCalculator = new SafePercentCalculator();
        List<Fund> funds = new ArrayList<>();
        funds.add(new Fund(1L, "Fundusz Polski 1", FundType.POLISH));
        funds.add(new Fund(2L, "Fundusz Polski 2", FundType.POLISH));
        funds.add(new Fund(3L, "Fundusz Zagraniczny 1", FundType.FOREIGN));
        funds.add(new Fund(4L, "Fundusz Zagraniczny 2", FundType.FOREIGN));
        funds.add(new Fund(5L, "Fundusz Pieniężny 1", FundType.FINANCIAL));
        funds.add(new Fund(6L, "Fundusz Pieniężny 2", FundType.FINANCIAL));

        BigDecimal result = percentCalculator.calculatePercent(FundType.FINANCIAL, funds);

        assertEquals( new BigDecimal(2.5), result);
    }

    @Test
    @PrepareForTest(FundType.class)
    public void unknowFundTest(){
        PercentCalculator percentCalculator = new SafePercentCalculator();
        List<Fund> funds = new ArrayList<>();
        funds.add(new Fund(1L, "Fundusz Polski 1", FundType.POLISH));
        funds.add(new Fund(2L, "Fundusz Polski 2", FundType.POLISH));
        funds.add(new Fund(3L, "Fundusz Zagraniczny 1", FundType.FOREIGN));
        funds.add(new Fund(4L, "Fundusz Zagraniczny 2", FundType.FOREIGN));
        funds.add(new Fund(5L, "Fundusz Zagraniczny 3", FundType.FOREIGN));
        funds.add(new Fund(6L, "Fundusz Pieniężny 1", FundType.FINANCIAL));

        FundType fundType = PowerMockito.mock(FundType.class);
        Whitebox.setInternalState(fundType, "name", "C");
        Whitebox.setInternalState(fundType, "ordinal", 3);

        PowerMockito.mockStatic(FundType.class);
        PowerMockito.when(FundType.values()).thenReturn(new FundType[]{FundType.POLISH, FundType.FOREIGN, FundType.FINANCIAL, fundType});

        BigDecimal result = percentCalculator.calculatePercent(fundType, funds);

        assertEquals(new BigDecimal(0), result);
    }

}
