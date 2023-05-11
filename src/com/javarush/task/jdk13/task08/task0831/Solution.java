package com.javarush.task.jdk13.task08.task0831;

import java.util.Arrays;

/* 
Любимые настолки
*/

public class Solution {

    public static BoardGame[] collection = new BoardGame[5];

    public static void main(String[] args) {
        BoardGame chess = new BoardGame();
        chess.name = "Шахматы";
        collection[0] = chess;

        BoardGame chess2 = new BoardGame();
        chess2.name = "Шахматы Блиц";
        collection[1] = chess2;

        BoardGame chess3 = new BoardGame();
        chess3.name = "Шахматы Супер-Блиц";
        collection[2] = chess3;

        BoardGame chess4 = new BoardGame();
        chess4.name = "Шахматы Фишера";
        collection[3] = chess4;

        BoardGame chess5 = new BoardGame();
        chess5.name = "Шахматы 3D";
        collection[4] = chess5;

        System.out.println(Arrays.toString(collection));
    }
}