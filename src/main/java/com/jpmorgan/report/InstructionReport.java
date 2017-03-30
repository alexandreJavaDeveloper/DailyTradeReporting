package com.jpmorgan.report;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jpmorgan.model.FinancialType;
import com.jpmorgan.model.Instruction;
import com.jpmorgan.rules.InstructionRules;

/**
 * Prepare and print the output text in the console of instructions data (trades) received by clients.
 */
public class InstructionReport
{
    // used to showing in the report formated by 2 fields after the comma (.). e.g: 2.727,65
    private final DecimalFormat decimalFormat;

    // helper to create the table using ASCII in the console as output text
    private final String leftAlignFormat;

    public InstructionReport()
    {
        this.decimalFormat = new DecimalFormat();
        this.decimalFormat.setMaximumFractionDigits(2);
        this.leftAlignFormat = "| %-17s | %-17s | %-19s | %-19s |%n";
    }

    /**
     * I choose only one list in the parameter because will be more easy to our clients
     * call this API. I could make them to send two lists (settled incoming everyday) and
     * (settled outgoing everyday) to make more easy for me, but I didn't.
     * No one says us that the FinancialType won't be changed.
     *
     * So, will be necessary to use the stream and Collectors API from Java 8 to create a Map of
     * each FinancialType from the list that is coming {@value trades} to have two lists
     * to iterate and make the logic is necessary.
     *
     * @param trades list of trade
     */
    public void showReport(final List<Instruction> trades)
    {
        float amountUSDIncoming = 0, amountUSDOutgoing = 0, rankingAmountUSDIncoming = 0, rankingAmountUSDOutgoing = 0;
        String rankingEntityNameIncoming = "Empty", rankingEntityNameOutgoing = "Empty";

        // using the stream of Java 8 to create a Map of each FinancialType (BUY, SELL)
        final Map<FinancialType, List<Instruction>> collect = trades.stream().collect(Collectors.groupingBy(Instruction::getFinancialType));

        final List<Instruction> listIncomingTrade = collect.getOrDefault(FinancialType.SELL, new ArrayList<>(0));

        final List<Instruction> listOutgoingTrade = collect.getOrDefault(FinancialType.BUY, new ArrayList<>(0));

        // FinancialType SELL
        for (final Instruction instruction : listIncomingTrade)
        {
            this.checkUpdates(instruction);

            final float currentAmountAsUSD = InstructionRules.getAmountOfTradeAsUSD(instruction);

            amountUSDIncoming += currentAmountAsUSD;

            if (rankingAmountUSDIncoming < currentAmountAsUSD)
            {
                rankingAmountUSDIncoming = currentAmountAsUSD;
                rankingEntityNameIncoming = instruction.getEntity();
            }
        }

        // FinancialType BUY
        for (final Instruction instruction : listOutgoingTrade)
        {
            this.checkUpdates(instruction);

            final float currentAmountAsUSD = InstructionRules.getAmountOfTradeAsUSD(instruction);

            amountUSDOutgoing += currentAmountAsUSD;

            if (rankingAmountUSDOutgoing < currentAmountAsUSD)
            {
                rankingAmountUSDOutgoing = currentAmountAsUSD;
                rankingEntityNameOutgoing = instruction.getEntity();
            }
        }

        this.printConsole(amountUSDIncoming, amountUSDOutgoing, rankingEntityNameIncoming, rankingEntityNameOutgoing);
    }

    /**
     * This check is made in this Report because if any client wants to get the real instruction
     * without change the date to the next working day, can have it. This is a rule for this case, this Report.
     *
     * @param instruction the instruction sent by the client
     */
    private void checkUpdates(final Instruction instruction)
    {
        if (!InstructionRules.isAvailableTrade(instruction))
            InstructionRules.updateSettlementDateToNextWorkingDay(instruction);
    }

    private void printConsole(final float amountUSDIncoming, final float amountUSDOutgoing, final String rankingEntityNameIncoming,
        final String rankingEntityNameOutgoing)
    {
        System.out.println("\n# Amount in USD settled:");

        System.out.format("+-------------------+-------------------+---------------------+---------------------+%n");
        System.out.format("| Incoming everyday | Outgoing everyday | Ranking of Incoming | Ranking of Outgoing |%n");
        System.out.format("+-------------------+-------------------+---------------------+---------------------+%n");

        System.out.format(this.leftAlignFormat, this.decimalFormat.format(amountUSDIncoming), this.decimalFormat.format(amountUSDOutgoing),
            rankingEntityNameIncoming, rankingEntityNameOutgoing);

        System.out.format("+-------------------+-------------------+---------------------+---------------------+%n");
    }
}