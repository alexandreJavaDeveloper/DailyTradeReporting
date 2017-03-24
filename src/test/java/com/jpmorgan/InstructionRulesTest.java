package com.jpmorgan;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import com.jpmorgan.exception.InvalidSettlementDateException;
import com.jpmorgan.model.CurrencyType;
import com.jpmorgan.model.FinancialType;
import com.jpmorgan.model.Instruction;
import com.jpmorgan.rules.InstructionRules;

public class InstructionRulesTest
{
    @Test
    public void testAvailableTrade()
    {
        final LocalDate instructionDate = LocalDate.now();
        // Friday
        LocalDate settlementDate = LocalDate.of(2017, 2, 24);

        Instruction instruction = new Instruction("foo", FinancialType.BUY, 0.50f, CurrencyType.SGP, instructionDate, settlementDate, 200, 100.25f);
        Assert.assertTrue(InstructionRules.isAvailableTrade(instruction));

        // Monday
        settlementDate = LocalDate.of(2017, 2, 20);
        instruction = new Instruction("foo", FinancialType.BUY, 0.50f, CurrencyType.SGP, instructionDate, settlementDate, 200, 100.25f);
        Assert.assertTrue(InstructionRules.isAvailableTrade(instruction));

        // Sunday
        settlementDate = LocalDate.of(2017, 2, 25);
        instruction = new Instruction("foo", FinancialType.BUY, 0.50f, CurrencyType.SGP, instructionDate, settlementDate, 200, 100.25f);
        Assert.assertFalse(InstructionRules.isAvailableTrade(instruction));
    }

    @Test
    public void testAvailableTradeBy_AED_and_SAR()
    {
        final LocalDate instructionDate = LocalDate.now();
        // Saturday
        LocalDate settlementDate = LocalDate.of(2017, 2, 25);

        Instruction instruction = new Instruction("foo", FinancialType.BUY, 0.50f, CurrencyType.AED, instructionDate, settlementDate, 200, 100.25f);
        Assert.assertFalse(InstructionRules.isAvailableTrade(instruction));

        // Monday
        settlementDate = LocalDate.of(2017, 2, 20);
        instruction = new Instruction("foo", FinancialType.BUY, 0.50f, CurrencyType.AED, instructionDate, settlementDate, 200, 100.25f);
        Assert.assertTrue(InstructionRules.isAvailableTrade(instruction));

        // Friday
        settlementDate = LocalDate.of(2017, 2, 24);
        instruction = new Instruction("foo", FinancialType.BUY, 0.50f, CurrencyType.SAR, instructionDate, settlementDate, 200, 100.25f);
        Assert.assertFalse(InstructionRules.isAvailableTrade(instruction));

        // Thursday
        settlementDate = LocalDate.of(2017, 2, 23);
        instruction = new Instruction("foo", FinancialType.BUY, 0.50f, CurrencyType.SAR, instructionDate, settlementDate, 200, 100.25f);
        Assert.assertTrue(InstructionRules.isAvailableTrade(instruction));
    }

    @Test
    public void testUnavailableTrade()
    {
        final LocalDate instructionDate = LocalDate.now();
        final LocalDate settlementDate = null;

        final Instruction instruction = new Instruction("foo", FinancialType.BUY, 0.50f, CurrencyType.AED, instructionDate, settlementDate, 200, 100.25f);
        Assert.assertFalse(InstructionRules.isAvailableTrade(instruction));

        // sending instruction as null
        Assert.assertFalse(InstructionRules.isAvailableTrade(null));
    }

    @Test
    public void testAmountOfTradeAsUSD()
    {
        final LocalDate instructionDate = LocalDate.now();
        final LocalDate settlementDate = LocalDate.of(2017, 2, 25);

        Instruction instruction = new Instruction("foo", FinancialType.BUY, 0.50f, CurrencyType.AED, instructionDate, settlementDate, 200, 100.25f);
        float amountOfTradeAsUSD = InstructionRules.getAmountOfTradeAsUSD(instruction);
        Assert.assertEquals(10025f, amountOfTradeAsUSD, 1);

        instruction = new Instruction("foo", FinancialType.BUY, 0.55f, CurrencyType.AED, instructionDate, settlementDate, 15, 100.28f);
        amountOfTradeAsUSD = InstructionRules.getAmountOfTradeAsUSD(instruction);
        Assert.assertEquals(827.31f, amountOfTradeAsUSD, 1);

        instruction = new Instruction("foo", FinancialType.BUY, 0f, CurrencyType.AED, instructionDate, settlementDate, 1, 0f);
        amountOfTradeAsUSD = InstructionRules.getAmountOfTradeAsUSD(instruction);
        Assert.assertEquals(0f, amountOfTradeAsUSD, 1);
    }

    @Test
    public void test() throws InvalidSettlementDateException
    {
        final LocalDate instructionDate = LocalDate.now();
        final LocalDate settlementDate = LocalDate.of(2017, 2, 25);

        final Instruction instruction = new Instruction("foo", FinancialType.BUY, 0.50f, CurrencyType.AED, instructionDate, settlementDate, 200, 100.25f);
        InstructionRules.updateSettlementDateToNextWorkingDay(instruction);
    }
}