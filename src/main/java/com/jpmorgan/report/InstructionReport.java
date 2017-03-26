package com.jpmorgan.report;

import java.text.DecimalFormat;
import java.util.List;

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
	private final String leftAlignFormat = "| %-17s | %-17s | %-19s | %-19s |%n";

	public InstructionReport()
	{
		this.decimalFormat = new DecimalFormat();
		this.decimalFormat.setMaximumFractionDigits(2);
	}
	/**
	 * I choose only one list in the parameter because will be more easy to our clients
	 * call this API. I could make them to send two lists (settled incoming everyday) and
	 * (settled outgoing everyday) to make more easy for me.
	 * So, I'll do some staffs to separate between (settled incoming everyday) and
	 * (settled outgoing everyday).
	 *
	 * @param trades list of trade
	 */
	public void showReport(final List<Instruction> trades)
	{
		float amountUSDIncoming = 0, amountUSDOutgoing = 0, rankingAmountUSDIncoming = 0, rankingAmountUSDOutgoing = 0;
		String rankingEntityNameIncoming = "Empty", rankingEntityNameOutgoing = "Empty";

		for (final Instruction instruction : trades)
		{
			// this check is made here because if any client wants to get the real instruction
			// without change the date to the next working day, can have it. This is a rule for this case.
			if (!InstructionRules.isAvailableTrade(instruction))
				InstructionRules.updateSettlementDateToNextWorkingDay(instruction);

			final float amountOfTradeAsUSD = InstructionRules.getAmountOfTradeAsUSD(instruction);

			if (FinancialType.BUY.equals(instruction.getFinancialType()))
			{
				amountUSDOutgoing += amountOfTradeAsUSD;

				if (rankingAmountUSDOutgoing < amountUSDOutgoing)
				{
					rankingAmountUSDOutgoing = amountUSDOutgoing;
					rankingEntityNameOutgoing = instruction.getEntity();
				}
			}
			else if (FinancialType.SELL.equals(instruction.getFinancialType())) // else if to ensure will have BUY and SELL only
			{
				amountUSDIncoming += amountOfTradeAsUSD;

				if (rankingAmountUSDIncoming < amountOfTradeAsUSD)
				{
					rankingAmountUSDIncoming = amountOfTradeAsUSD;
					rankingEntityNameIncoming = instruction.getEntity();
				}
			}
		}

		this.printConsole(amountUSDIncoming, amountUSDOutgoing, rankingEntityNameIncoming, rankingEntityNameOutgoing);
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