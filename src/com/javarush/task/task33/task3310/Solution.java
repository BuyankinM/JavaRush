package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10_000);
        testStrategy(new OurHashMapStorageStrategy(), 10_000);
        //testStrategy(new FileStorageStrategy(), 1_000);
        testStrategy(new OurHashBiMapStorageStrategy(), 10_000);
        testStrategy(new HashBiMapStorageStrategy(), 10_000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10_000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> ids = new HashSet<>();
        for (String s : strings) ids.add(shortener.getId(s));
        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> strings = new HashSet<>();
        for (Long key : keys) strings.add(shortener.getString(key));
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        System.out.println(strategy.getClass().getSimpleName());

        Set<String> strings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) strings.add(Helper.generateRandomString());

        Shortener shortener = new Shortener(strategy);
        Date start = new Date();
        Set<Long> ids = getIds(shortener, strings);
        Date end = new Date();
        System.out.printf("Time for getIds = %d ms\n", end.getTime() - start.getTime());

        start = new Date();
        Set<String> stringsRes = getStrings(shortener, ids);
        end = new Date();
        System.out.printf("Time for getStrings = %d ms\n", end.getTime() - start.getTime());

        if (stringsRes.containsAll(strings) && strings.containsAll(stringsRes)) {
            System.out.println("Тест пройден.");
        } else {
            System.out.println("Тест не пройден.");
        }
    }
}
