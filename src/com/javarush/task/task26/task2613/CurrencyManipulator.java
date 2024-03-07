package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;
import java.util.stream.Collectors;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode.toUpperCase();
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        Integer amount = denominations.getOrDefault(denomination, 0);
        denominations.put(denomination, amount + count);
    }

    public int getTotalAmount() {
        return denominations
                .entrySet()
                .stream()
                .map(e -> e.getKey() * e.getValue())
                .reduce(0, Integer::sum);
    }

    public boolean hasMoney() {
        return getTotalAmount() > 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> money = new TreeMap<>(Comparator.reverseOrder());
        List<Integer> nominals = denominations
                .keySet()
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        if (!findOptimalBanknotes(money, nominals, 0, expectedAmount)) {
            throw new NotEnoughMoneyException();
        }

        for (Map.Entry<Integer, Integer> entry : money.entrySet()) {
            Integer nominal = entry.getKey();
            int value = denominations.get(nominal) - entry.getValue();

            if (value > 0) denominations.put(nominal, value);
            else denominations.remove(nominal);
        }

        return money;
    }

    private boolean findOptimalBanknotes(Map<Integer, Integer> money,
                                         List<Integer> nominals,
                                         int idx,
                                         int expectedAmount) {
        if (idx == nominals.size() || expectedAmount == 0) {
            return expectedAmount == 0;
        }

        for (int i = idx; i < nominals.size(); i++) {
            Integer nominal = nominals.get(i);
            int num = Math.min(expectedAmount / nominal, denominations.get(nominal));

            for (int j = num; j >= 0; j--) {
                if (j > 0) money.put(nominal, j);
                else money.remove(nominal);

                if (findOptimalBanknotes(money, nominals, i + 1, expectedAmount - nominal * j))
                    return true;
            }
        }
        return false;
    }
}
