package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info");

    @Override
    public void execute() {
        System.out.println(res.getString("before"));

        boolean hasMoney = false;
        for (CurrencyManipulator cm : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (cm.hasMoney()) {
                System.out.println(cm.getCurrencyCode() + " - " + cm.getTotalAmount());
                hasMoney = true;
            }
        }

        if (!hasMoney) System.out.println(res.getString("no.money"));

        System.out.println("*******************************");
    }
}