package me.michalik.blueservice.domain;

import java.util.Objects;

public class Fund{

    private Long id;
    private String name;
    private FundType type;

    public Fund(Long id, String name, FundType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FundType getType() {
        return type;
    }

    public void setType(FundType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fund fund = (Fund) o;
        return Objects.equals(id, fund.id) &&
                Objects.equals(name, fund.name) &&
                type == fund.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }
}
