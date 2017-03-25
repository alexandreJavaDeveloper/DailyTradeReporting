package com.jpmorgan.report;

import java.util.ArrayList;
import java.util.List;

import com.jpmorgan.model.FinancialType;
import com.jpmorgan.model.Instruction;

public class InstructionReport
{
	/**
	 * I choose only one list in the parameter because will be more easy to our clients
	 * call this API. I could make them to send two lists (settled incoming everyday) and
	 * (settled outgoing everyday) to make more easy for me.
	 * So, I'll do some staffs to separate between (settled incoming everyday) and
	 * (settled outgoing everyday).
	 *
	 * @param trades
	 */
	public void showReport(final List<Instruction> trades)
	{
		final List<Instruction> listOutgoing = new ArrayList<>();
		final List<Instruction> listIncoming = new ArrayList<>();

		for (final Instruction instruction : trades)
		{
			if (FinancialType.BUY.equals(instruction.getFinancialType()))
				listOutgoing.add(instruction);
			else if (FinancialType.SELL.equals(instruction.getFinancialType())) // else if to ensure will have BUY and SELL only
				listIncoming.add(instruction);
		}

		// TODO já posso ir fazendo a somatória no for anterior

		//		Collections.sort(trades);
	}
}