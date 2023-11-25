package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;

/* 
Построй дерево(1)
*/

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    int curSize;

    public CustomTree() {
        this.root = new Entry<>("root");
        curSize = 0;
    }

    @Override
    public boolean add(String s) {
        boolean isBlockedTree = testBlockedTree();

        ArrayDeque<Entry<String>> levelDeq = new ArrayDeque<>();
        levelDeq.add(root);

        while (true) {
            int l = levelDeq.size();
            for (int i = 0; i < l; i++) {
                Entry<String> node = levelDeq.removeLast();
                if (isBlockedTree && node.isBlocked()) {
                    node.unBlock();
                }

                if (node.hasLeftChild()) {
                    levelDeq.addFirst(node.leftChild);
                } else if (node.availableToAddLeftChildren) {
                    addChild(node, s, true);
                    return true;
                }

                if (node.hasRightChild()) {
                    levelDeq.addFirst(node.rightChild);
                } else if (node.availableToAddRightChildren) {
                    addChild(node, s, false);
                    return true;
                }
            }
        }
    }

    private void addChild(Entry<String> parent, String s, boolean isLeftChild) {
        Entry<String> newChild = new Entry<>(s);
        newChild.parent = parent;

        if (isLeftChild) parent.setLeftChild(newChild);
        else parent.setRightChild(newChild);

        curSize++;
    }

    public String getParent(String s) {
        ArrayDeque<Entry<String>> levelDeq = new ArrayDeque<>();
        levelDeq.add(root);

        while (!levelDeq.isEmpty()) {
            Entry<String> node = levelDeq.removeLast();
            if (node.elementName.equals(s)) return node.parent.elementName;
            if (node.hasLeftChild()) levelDeq.addFirst(node.leftChild);
            if (node.hasRightChild()) levelDeq.addFirst(node.rightChild);
        }
        return null;
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof String)) {
            throw new UnsupportedOperationException();
        }
        String name = (String) o;
        ArrayDeque<Entry<String>> levelDeq = new ArrayDeque<>();
        levelDeq.add(root);

        boolean result = false;
        Entry<String> node = null;

        while (!levelDeq.isEmpty()) {
            node = levelDeq.removeLast();
            if (node.elementName.equals(name)) {
                result = true;
                break;
            }
            if (node.hasLeftChild()) levelDeq.addFirst(node.leftChild);
            if (node.hasRightChild()) levelDeq.addFirst(node.rightChild);
        }

        if (result) {
            deleteSubTree(node);
            if (node.isLeftChild) node.parent.leftChild = null;
            else if (node.isRightChild) node.parent.rightChild = null;
        }

        return result;
    }

    private void deleteSubTree(Entry<String> rootSub) {
        ArrayDeque<Entry<String>> levelDeq = new ArrayDeque<>();
        levelDeq.add(rootSub);
        while (!levelDeq.isEmpty()) {
            Entry<String> node = levelDeq.removeLast();
            if (node.hasLeftChild()) {
                levelDeq.addFirst(node.leftChild);
                node.leftChild.parent = null;
                node.leftChild = null;
            }
            if (node.hasRightChild()) {
                levelDeq.addFirst(node.rightChild);
                node.rightChild.parent = null;
                node.rightChild = null;
            }
            curSize--;
        }
    }

    private boolean testBlockedTree() {
        ArrayDeque<Entry<String>> levelDeq = new ArrayDeque<>();
        levelDeq.add(root);

        while (!levelDeq.isEmpty()) {
            Entry<String> node = levelDeq.removeLast();
            if (node.isAvailableToAddChildren()) return false;
            if (node.hasLeftChild()) levelDeq.addFirst(node.leftChild);
            if (node.hasRightChild()) levelDeq.addFirst(node.rightChild);
        }
        return true;
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return curSize;
    }

    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        boolean isLeftChild, isRightChild;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
            this.isLeftChild = false;
            this.isRightChild = false;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }

        public void setLeftChild(Entry<T> leftChild) {
            this.leftChild = leftChild;
            this.availableToAddLeftChildren = false;
            leftChild.isLeftChild = true;
        }

        public void setRightChild(Entry<T> rightChild) {
            this.rightChild = rightChild;
            this.availableToAddRightChildren = false;
            rightChild.isRightChild = true;
        }

        public boolean hasLeftChild() {
            return leftChild != null;
        }

        public boolean hasRightChild() {
            return rightChild != null;
        }

        public boolean isBlocked() {
            return !availableToAddLeftChildren
                    && !availableToAddRightChildren
                    && !hasLeftChild()
                    && !hasRightChild();
        }

        public void unBlock() {
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }
    }
}
