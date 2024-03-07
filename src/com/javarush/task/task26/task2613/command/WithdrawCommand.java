package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));

        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        int sum;
        TreeMap<Integer, Integer> banknotes;
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            try {
                sum = Integer.parseInt(ConsoleHelper.readString());
                if (manipulator.isAmountAvailable(sum)) {
                    banknotes = (TreeMap<Integer, Integer>) manipulator.withdrawAmount(sum);
                    break;
                } else {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                }
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }

        for (Map.Entry<Integer, Integer> entry : banknotes.entrySet()) {
            ConsoleHelper.writeMessage(String.format("\t%d - %d", entry.getKey(), entry.getValue()));
        }
        ConsoleHelper.writeMessage(String.format(res.getString("specify.amount"), sum, currencyCode));
    }
}