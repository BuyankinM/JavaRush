package com.javarush.task.task37.task3701;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/* 
Круговой итератор
*/

public class Solution<T> extends ArrayList<T> {
    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new RoundIterator();
    }

    public class RoundIterator implements Iterator<T>{
        private int currentIndex;
        private Iterator<T> itr = Solution.super.iterator();
        @Override
        public boolean hasNext() {
            return size() > 0;
        }

        @Override
        public T next() {
            if (currentIndex == size()) {
                itr = Solution.super.iterator();
                currentIndex = 0;
            }
            currentIndex++;
            return itr.next();
        }

        @Override
        public void remove() {
            itr.remove();
        }
    }
}
