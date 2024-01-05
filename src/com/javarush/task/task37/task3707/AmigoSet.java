package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        int size = Math.max(16, (int) Math.ceil(collection.size() / .75f));
        this.map = new HashMap<>(size);
        this.addAll(collection);
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) == null;
    }

    @Override
    public Object clone() {
        AmigoSet<E> o = null;
        try {
            o = new AmigoSet<>();
            o.map = (HashMap<E, Object>) map.clone();
        } catch (Exception e) {
            throw new InternalError(e);
        }
        return o;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();

        stream.writeInt(map.keySet().size());
        stream.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        stream.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        for (E key : map.keySet()) {
            stream.writeObject(key);
        }
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();

        int size = stream.readInt();
        int capacity = stream.readInt();
        float loadFactor = stream.readFloat();

        map = new HashMap<>(capacity, loadFactor);
        for (int i = 0; i < size; i++) {
            add((E) stream.readObject());
        }
    }
}
