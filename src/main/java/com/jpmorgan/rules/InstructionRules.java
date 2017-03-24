package com.jpmorgan.rules;

import java.time.DayOfWeek;
import java.time.LocalDate;

import com.jpmorgan.exception.InvalidSettlementDateException;
import com.jpmorgan.model.CurrencyType;
import com.jpmorgan.model.Instruction;

/**
 * Contains instruction trade Rules.
 */
public class InstructionRules
{
    /**
     * Return USD amount of a trade.
     * The rule is: (Price per unit * Units * Agreed Fx)
     *
     * @param instruction
     * @return amount of trade in USD financial type value
     */
    public static float getAmountOfTradeAsUSD(final Instruction instruction)
    {
        return instruction.getPricePerUnit() * instruction.getUnits() * instruction.getAgreedFx();
    }

    /**
     * TODO
     *
     * @param instruction
     * @throws InvalidSettlementDateException
     */
    public static void updateSettlementDateToNextWorkingDay(final Instruction instruction) throws InvalidSettlementDateException
    {
        int countTried = 7; // max day of a week

        while (!InstructionRules.isAvailableTrade(instruction))
        {
            // Didn't find the next working day
            if (countTried-- > 0)
                throw new InvalidSettlementDateException();

            final LocalDate nextDay = instruction.getSettlementDate().plusDays(1);
            instruction.setSettlementDate(nextDay);
        }
    }

    /**
     * Check if the @param instruction is a working day to be available to make a trade.
     *
     * To check it, needs to test the following rules:
     *      - A work week starts Monday and ends Friday, unless the currency of the trade is AED or SAR, where
     *          the work week starts Sunday and ends Thursday. No other holidays to be taken into account.
     *
     * @param instruction
     * @return is working day
     */
    public static boolean isAvailableTrade(final Instruction instruction)
    {
        if (instruction == null || instruction.getSettlementDate() == null)
            return false;

        return InstructionRules.isWorkingDay(instruction);
    }

    private static boolean isWorkingDay(final Instruction instruction)
    {
        // Sunday or Monday, Tuesday, Wednesday or Thursday
        if (InstructionRules.isCurrencyTypeAED_or_SAR(instruction))
            return instruction.getSettlementDate().getDayOfWeek() == DayOfWeek.SUNDAY
            || instruction.getSettlementDate().getDayOfWeek().getValue() <= DayOfWeek.THURSDAY.getValue();

        // From Monday to Friday
        return instruction.getSettlementDate().getDayOfWeek().getValue() <= DayOfWeek.FRIDAY.getValue();
    }

    private static boolean isCurrencyTypeAED_or_SAR(final Instruction instruction)
    {
        return instruction.getCurrencyType() == CurrencyType.AED || instruction.getCurrencyType() == CurrencyType.SAR;
    }
}