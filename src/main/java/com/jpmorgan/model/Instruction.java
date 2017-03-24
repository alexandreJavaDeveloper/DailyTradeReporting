package com.jpmorgan.model;

import java.util.Date;

/**
 * Represents the instructions sent by various clients to JP Morgan to execute in the international market.
 */
public class Instruction
{
    private String entity;

    private Instruction financialEntity;

    private float agreedFx;

    private CurrencyType currencyType;

    private Date instructionDate;

    private Date settlementDate;

    private int units;

    private float pricePerUnit;

    public Instruction(final String entity, final Instruction financialEntity, final float agreedFx, final CurrencyType currencyType,
        final Date instructionDate, final Date settlementDate, final int units, final float pricePerUnit)
    {
        this.entity = entity;
        this.financialEntity = financialEntity;
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

    public Instruction getFinancialEntity()
    {
        return this.financialEntity;
    }

    public void setFinancialEntity(final Instruction financialEntity)
    {
        this.financialEntity = financialEntity;
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

    public Date getInstructionDate()
    {
        return this.instructionDate;
    }

    public void setInstructionDate(final Date instructionDate)
    {
        this.instructionDate = instructionDate;
    }

    public Date getSettlementDate()
    {
        return this.settlementDate;
    }

    public void setSettlementDate(final Date settlementDate)
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
        return "FinancialEntity [entity=" + this.entity + ", financialEntity=" + this.financialEntity + ", agreedFx=" + this.agreedFx + ", currencyType="
            + this.currencyType + ", instructionDate=" + this.instructionDate + ", settlementDate=" + this.settlementDate + ", units=" + this.units
            + ", pricePerUnit=" + this.pricePerUnit + "]";
    }
}