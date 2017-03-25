package com.jpmorgan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.model.CurrencyType;
import com.jpmorgan.model.FinancialType;
import com.jpmorgan.model.Instruction;

public class InstructionReportTest
{
	private List<Instruction> trades;

	@Before
	public void before()
	{
		this.trades = new ArrayList<>();

		LocalDate settlementDate = LocalDate.of(2017, 2, 26);
		Instruction instruction = new Instruction("foo", FinancialType.BUY, 0.50f, CurrencyType.SAR, LocalDate.now(), settlementDate, 200, 100.25f);
		this.trades.add(instruction);

		settlementDate = LocalDate.of(2017, 2, 24);
		instruction = new Instruction("test", FinancialType.SELL, 0.53f, CurrencyType.SAR, LocalDate.now(), settlementDate, 130, 15.15f);
		this.trades.add(instruction);

		settlementDate = LocalDate.of(2017, 2, 22);
		instruction = new Instruction("bar", FinancialType.SELL, 10.14f, CurrencyType.SGP, LocalDate.now(), settlementDate, 55, 20.95f);
		this.trades.add(instruction);

		settlementDate = LocalDate.of(2017, 2, 20);
		instruction = new Instruction("you", FinancialType.BUY, 1.10f, CurrencyType.SAR, LocalDate.now(), settlementDate, 15, 50.00f);
		this.trades.add(instruction);

		settlementDate = LocalDate.of(2017, 2, 15);
		instruction = new Instruction("me", FinancialType.BUY, 56.14f, CurrencyType.SGP, LocalDate.now(), settlementDate, 5, 97.45f);
		this.trades.add(instruction);

		settlementDate = LocalDate.of(2017, 2, 15);
		instruction = new Instruction("lets go", FinancialType.SELL, 16.20f, CurrencyType.SGP, LocalDate.now(), settlementDate, 50, 100.00f);
		this.trades.add(instruction);
	}

	@Test
	public void test()
	{

	}
}