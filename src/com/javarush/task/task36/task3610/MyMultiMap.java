package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int size = 0;
        for (List<V> list: map.values()) size += list.size();
        return size;
    }

    @Override
    public V put(K key, V value) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<V>() {{
                add(value);
            }});
            this.put(key, value);

            return null;
        } else {
            List<V> list = map.get(key);
            V val = list.get(list.size() - 1);

            // check repeat size
            if (list.size() == repeatCount) {
                list.remove(0);
            }
            list.add(value);
            return val;
        }
    }

    @Override
    public V remove(Object key) {
        V val = null;
        if (map.containsKey(key)) {
            List<V> list = map.get(key);
            val = list.remove(0);
            if (list.isEmpty()) map.remove(key);
        }
        return val;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        List<V> list = new ArrayList<>();
        for (List<V> l : map.values()) {
            list.addAll(l);
        }
        return list;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (List<V> list : map.values()) {
            for (V val : list) {
                if (val.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}