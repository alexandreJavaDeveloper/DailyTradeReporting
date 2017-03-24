package com.jpmorgan.model;

import com.jpmorgan.model.FinancialType;

import java.time.LocalDate;

/**
 * Represents the instructions sent by various clients to JP Morgan to execute in the international market.
 */
public class Instruction
{
    private String entity;

    private final FinancialType financialType;

    private float agreedFx;

    private CurrencyType currencyType;

    private LocalDate instructionDate;

    private LocalDate settlementDate;

    private int units;

    private float pricePerUnit;

    public Instruction(final String entity, final FinancialType financialType, final float agreedFx, final CurrencyType currencyType,
        final LocalDate instructionDate, final LocalDate settlementDate, final int units, final float pricePerUnit)
    {
        super();
        this.entity = entity;
        this.financialType = financialType;
        this.agreedFx = agreedFx;
        this.currencyType = currencyType;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
    }

    public String getEntity()
    {
        return this.entity;
    }

    public void setEntity(final String entity)
    {
        this.entity = entity;
    }

    public float getAgreedFx()
    {
        return this.agreedFx;
    }

    public void setAgreedFx(final float agreedFx)
    {
        this.agreedFx = agreedFx;
    }

    public CurrencyType getCurrencyType()
    {
        return this.currencyType;
    }

    public void setCurrencyType(final CurrencyType currencyType)
    {
        this.currencyType = currencyType;
    }

    public LocalDate getInstructionDate()
    {
        return this.instructionDate;
    }

    public void setInstructionDate(final LocalDate instructionDate)
    {
        this.instructionDate = instructionDate;
    }

    public LocalDate getSettlementDate()
    {
        return this.settlementDate;
    }

    public void setSettlementDate(final LocalDate settlementDate)
    {
        this.settlementDate = settlementDate;
    }

    public int getUnits()
    {
        return this.units;
    }

    public void setUnits(final int units)
    {
        this.units = units;
    }

    public float getPricePerUnit()
    {
        return this.pricePerUnit;
    }

    public void setPricePerUnit(final float pricePerUnit)
    {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public String toString()
    {
        return "Instruction [entity=" + this.entity + ", financialType=" + this.financialType + ", agreedFx=" + this.agreedFx + ", currencyType="
            + this.currencyType + ", instructionDate=" + this.instructionDate + ", settlementDate=" + this.settlementDate + ", units=" + this.units
            + ", pricePerUnit=" + this.pricePerUnit + "]";
    }
}