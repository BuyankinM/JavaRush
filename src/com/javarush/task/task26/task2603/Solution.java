package com.javarush.task.task26.task2603;

import java.util.Arrays;
import java.util.Comparator;

/* 
Убежденному убеждать других не трудно
*/

public class Solution {
    public static void main(String[] args) {
        // Пример использования сортировки списка строк
        String[] names = {"Alice", "Bob", "Carol", "David", "Eve"};

        // Создаем компараторы для сортировки по длине и лексикографическому порядку
        Comparator<String> lengthComparator = Comparator.comparing(String::length);
        Comparator<String> lexicalComparator = Comparator.naturalOrder();

        // Создаем объект CustomizedComparator с последовательностью компараторов
        CustomizedComparator<String> customizedComparator = new CustomizedComparator<>(lengthComparator, lexicalComparator);

        // Сортируем массив и выводим результат
        Arrays.sort(names, customizedComparator);
        System.out.println(Arrays.toString(names));
    }

    public static class CustomizedComparator<T> implements Comparator<T> {
        private Comparator<T>[] comparators;

        public CustomizedComparator(Comparator<T>... comparators) {
            this.comparators = comparators;
        }

        @Override
        public int compare(T o1, T o2) {
            int res = 0;
            for (Comparator<T> comp: comparators) {
                res = comp.compare(o1, o2);
                if (res != 0) return res;
            }
            return res;
        }
    }
}