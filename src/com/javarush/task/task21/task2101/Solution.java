package com.javarush.task.task21.task2101;

/* 
Определяем адрес сети
*/

public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        byte[] netAddress = new byte[ip.length];
        for (int i = 0; i < ip.length; i++) netAddress[i] = (byte) (ip[i] & mask[i]);
        return netAddress;
    }

    public static void print(byte[] bytes) {
        for (byte b : bytes) {
            String res = Integer.toBinaryString(b);
            if (res.length() < 32) {
                res = String.format("%0"+ (32 - res.length()) +"d%s", 0, res);
            }
            System.out.printf("%s ", res.substring(24));
        }
        System.out.println();
    }
}
