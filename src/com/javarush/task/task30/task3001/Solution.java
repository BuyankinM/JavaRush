package com.javarush.task.task30.task3001;

import java.math.BigInteger;

/* 
Конвертер систем счислений
*/

public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumberSystemType._10, "6");
        Number result = convertNumberToOtherNumberSystem(number, NumberSystemType._2);
        System.out.println(result);    //expected 110

        number = new Number(NumberSystemType._16, "6df");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._8);
        System.out.println(result);    //expected 3337

        number = new Number(NumberSystemType._16, "abcdefabcdef");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._16);
        System.out.println(result);    //expected abcdefabcdef

        number = new Number(NumberSystemType._8, "0");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._16);
        System.out.println(result);    //NumberFormatException
    }

    public static Number convertNumberToOtherNumberSystem(Number number, NumberSystem expectedNumberSystem) {
        int currentNumberSystemInt = number.getNumberSystem().getNumberSystemIntValue();

        String numberStr = number.getDigit();
        String numberCode = "0123456789abcdef";
        String pattern = "[" + numberCode.substring(0, currentNumberSystemInt) + "]+";
        if (!numberStr.matches(pattern)) {
            throw new NumberFormatException();
        }

        int newNumberSystemInt = expectedNumberSystem.getNumberSystemIntValue();
        if (newNumberSystemInt == currentNumberSystemInt) {
            return number;
        }

        int length = numberStr.length();
        BigInteger res10 = BigInteger.ZERO;
        BigInteger base = new BigInteger(String.valueOf(currentNumberSystemInt));

        for (int i = 0; i < length; i++) {
            int val = numberCode.indexOf(numberStr.charAt(length - i - 1));
            res10 = res10.add(base.pow(i).multiply(new BigInteger(String.valueOf(val))));
        }

        BigInteger newBase = new BigInteger(String.valueOf(newNumberSystemInt));
        StringBuilder res = new StringBuilder();
        boolean firstStep = true;

        while (firstStep || !res10.equals(BigInteger.ZERO)) {
            int ind = res10.remainder(newBase).intValue();
            res.insert(0, numberCode.charAt(ind));
            res10 = res10.divide(newBase);
            firstStep = false;
        }

        return new Number(expectedNumberSystem, res.toString());
    }
}
