package com.jpmorgan.model;

import java.time.LocalDate;

import com.jpmorgan.rules.InstructionRules;

/**
 * Represents the instructions sent by various clients to JP Morgan to execute in the international market.
 */
public class Instruction implements Comparable<Instruction>
{
	private final String entity;

	private final FinancialType financialType;

	private final float agreedFx;

	private final CurrencyType currencyType;

	private final LocalDate instructionDate;

	private LocalDate settlementDate;

	private final int units;

	private final float pricePerUnit;

	public Instruction(final String entity, final FinancialType financialType, final float agreedFx, final CurrencyType currencyType,
			final LocalDate instructionDate, final LocalDate settlementDate, final int units, final float pricePerUnit)
	{
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

	public FinancialType getFinancialType()
	{
		return this.financialType;
	}

	public float getAgreedFx()
	{
		return this.agreedFx;
	}

	public CurrencyType getCurrencyType()
	{
		return this.currencyType;
	}

	public LocalDate getInstructionDate()
	{
		return this.instructionDate;
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

	public float getPricePerUnit()
	{
		return this.pricePerUnit;
	}

	@Override
	public String toString()
	{
		return "Instruction [entity=" + this.entity + ", financialType=" + this.financialType + ", agreedFx=" + this.agreedFx + ", currencyType="
				+ this.currencyType + ", instructionDate=" + this.instructionDate + ", settlementDate=" + this.settlementDate + ", units=" + this.units
				+ ", pricePerUnit=" + this.pricePerUnit + "]";
	}

	@Override
	public int compareTo(final Instruction another)
	{
		return (int) InstructionRules.getAmountOfTradeAsUSD(this) - (int)InstructionRules.getAmountOfTradeAsUSD(another);
	}
}