package com.javarush.task.pro.task09.task0908;

/*
Двоично-шестнадцатеричный конвертер
*/

public class Solution {

    private static String HEX = "0123456789abcdef";

    public static void main(String[] args) {
        String binaryNumber = "110";
        System.out.println("Двоичное число " + binaryNumber + " равно шестнадцатеричному числу " + toHex(binaryNumber));
        String hexNumber = "6";
        System.out.println("Шестнадцатеричное число " + hexNumber + " равно двоичному числу " + toBinary(hexNumber));
    }

    public static String toHex(String binaryNumber) {
        if (binaryNumber == ""
                || binaryNumber == null
                || !binaryNumber.matches("[01]+")) {
            return "";
        }

        binaryNumber = correctBinaryBlock(binaryNumber);
        String hex = "";
        
        for (int i = 0; i < binaryNumber.length(); i += 4) {
            String block = binaryNumber.substring(i, i + 4);

            int dec = 0;
            for (int j = 0; j < 4; j++) {
                dec += (block.charAt(3 - j) == '1' ? 1 : 0) * Math.pow(2, j);
            }
            hex += HEX.charAt(dec);
        }
        return hex;
    }

    public static String toBinary(String hexNumber) {
        if (hexNumber == ""
                || hexNumber == null
                || !hexNumber.matches("[0-9a-f]+")) {
            return "";
        }

        String bin = "";
        for (int i = 0; i < hexNumber.length(); i++) {
            int dec = HEX.indexOf(hexNumber.charAt(i));
            String bin_block = "";

            if (dec > 0) {
                while (dec > 0) {
                    bin_block = (dec % 2 == 1 ? "1" : "0") + bin_block;
                    dec /= 2;
                }
            } else {
                bin_block = "0000";
            }

            bin_block = correctBinaryBlock(bin_block);
            bin += bin_block;
        }
        return bin;
    }

    private static String correctBinaryBlock(String binary) {
        int l = binary.length();
        if (l % 4 > 0) {
            binary = "0".repeat(4 - (l % 4)) + binary;
        }
        return binary;
    }
}
