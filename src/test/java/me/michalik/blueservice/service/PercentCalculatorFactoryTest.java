package me.michalik.blueservice.service;

import me.michalik.blueservice.domain.InvestmentStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)
public class PercentCalculatorFactoryTest {

    @Test
    public void createSafePercentCalculator(){
        PercentCalculator percentCalculator = PercentCalculatorFactory.createPercentCalculator(InvestmentStyle.SAFE);

        assertTrue(percentCalculator instanceof SafePercentCalculator);
    }

    @Test
    public void createAggressivePercentCalculator(){
        PercentCalculator percentCalculator = PercentCalculatorFactory.createPercentCalculator(InvestmentStyle.AGGRESSIVE);

        assertTrue(percentCalculator instanceof AggressivePercentCalculator);
    }

    @Test
    public void createBalancedPercentCalculator(){
        PercentCalculator percentCalculator = PercentCalculatorFactory.createPercentCalculator(InvestmentStyle.BALANCED);

        assertTrue(percentCalculator instanceof BalancedPercentCalculator);
    }

    @Test
    @PrepareForTest(InvestmentStyle.class)
    public void createUnknowPercentCalculator(){
        InvestmentStyle investmentStyle = PowerMockito.mock(InvestmentStyle.class);
        Whitebox.setInternalState(investmentStyle, "name", "C");
        Whitebox.setInternalState(investmentStyle, "ordinal", 3);

        PowerMockito.mockStatic(InvestmentStyle.class);
        PowerMockito.when(InvestmentStyle.values()).thenReturn(new InvestmentStyle[]{InvestmentStyle.SAFE, InvestmentStyle.AGGRESSIVE, InvestmentStyle.BALANCED, investmentStyle});

        PercentCalculator percentCalculator = PercentCalculatorFactory.createPercentCalculator(investmentStyle);

        assertNull(percentCalculator);
    }

}
