package com.jpmorgan.model;

public enum CurrencyType
{
    SGP("GSP"), AED("AED");

    private String value;

    private CurrencyType(final String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return this.value;
    }
}