package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private static final String cardCor = "123456789012";
    private static final String pinCor = "1234";

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login");

    @Override
    public void execute() throws InterruptOperationException {
        System.out.println(res.getString("before"));

        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));

            String creditCardNumber = ConsoleHelper.readString();
            String pinStr = ConsoleHelper.readString();

            if (!creditCardNumber.matches("\\d{12}")
                    || !pinStr.matches("\\d{4}")) {

                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            } else {
                if (validCreditCards.containsKey(creditCardNumber)
                        && validCreditCards.getString(creditCardNumber).equals(pinStr)) {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), creditCardNumber));
                    break;
                } else {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), creditCardNumber));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                }
            }
        }
    }
}
