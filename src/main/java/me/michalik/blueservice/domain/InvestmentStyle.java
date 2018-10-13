package me.michalik.blueservice.domain;

public enum InvestmentStyle {

    // Bezpieczny
    SAFE(20, 75, 5),
    // Zrównoważony
    BALANCED(30, 60, 10),
    // Agresywny
    AGGRESSIVE(40, 20, 40);

    private int polishPercent;
    private int foreignPercent;
    private int financialPercent;

    InvestmentStyle(int polishPercent, int foreignPercent, int financialPercent) {
        this.polishPercent = polishPercent;
        this.foreignPercent = foreignPercent;
        this.financialPercent = financialPercent;
    }

    public int getPolishPercent() {
        return polishPercent;
    }

    public int getForeignPercent() {
        return foreignPercent;
    }

    public int getFinancialPercent() {
        return financialPercent;
    }

}
