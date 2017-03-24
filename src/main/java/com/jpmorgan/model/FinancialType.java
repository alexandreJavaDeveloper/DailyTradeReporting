package com.jpmorgan.model;

public enum FinancialType
{
    BUY('B'), SELL('S');

    private char value;

    private FinancialType(final char value)
    {
        this.value = value;
    }

    public char getValue()
    {
        return this.value;
    }
}