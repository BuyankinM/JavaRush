package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit");
    @Override
    public void execute() throws InterruptOperationException {
        System.out.println(res.getString("before"));

        String currencyCode = ConsoleHelper.askCurrencyCode();
        String[] depositData = ConsoleHelper.getValidTwoDigits(currencyCode);

        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        int denomination = Integer.parseInt(depositData[0]);
        int count = Integer.parseInt(depositData[1]);
        currencyManipulator.addAmount(denomination, count);

        System.out.println(String.format(res.getString("success.format"), denomination * count, currencyCode));
    }
}
