package com.javarush.task.task26.task2613;

import java.util.*;
import java.util.stream.Collectors;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        String currencyCodeToFind = currencyCode.toLowerCase();
        CurrencyManipulator currencyManipulator = map.get(currencyCodeToFind);
        if (currencyManipulator == null) {
            currencyManipulator = new CurrencyManipulator(currencyCodeToFind);
            map.put(currencyCodeToFind, currencyManipulator);
        }
        return currencyManipulator;
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return map.values();
    }
}