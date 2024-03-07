package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String in = "";
        try {
            in = bis.readLine();
            if (in.toUpperCase().equals("EXIT")) {
                writeMessage(res.getString("the.end"));
                throw new InterruptOperationException();
            }
        } catch (IOException e) {
        }
        return in;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String code;
        while (true) {
            writeMessage(res.getString("choose.currency.code"));
            if ((code = readString()).length() == 3)
                break;
            writeMessage(res.getString("invalid.data"));
        }
        return code.toUpperCase();
    }

    public static Operation askOperation() throws InterruptOperationException {
        Operation op;
        while (true) {
            writeMessage(res.getString("choose.operation"));
            writeMessage(res.getString("operation.INFO"));
            writeMessage(res.getString("operation.DEPOSIT"));
            writeMessage(res.getString("operation.WITHDRAW"));
            writeMessage(res.getString("operation.EXIT"));
            try {
                op = Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
                break;
            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
        return op;
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] data = new String[2];
        while (true) {
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            try {
                String in = readString();
                String[] values = in.split("\\s+");
                if (values.length != 2) {
                    writeMessage(res.getString("invalid.data"));
                } else {
                    if (Integer.parseInt(values[0]) > 0 && Integer.parseInt(values[1]) > 0) {
                        data[0] = values[0];
                        data[1] = values[1];
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
        return data;
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }
}
