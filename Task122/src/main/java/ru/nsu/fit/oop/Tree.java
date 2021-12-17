package ru.nsu.fit.oop;

import java.util.*;

public class Tree<E> implements Collection<E> {
    private E val;
    private Tree<E> parent, left, right;

    private int lsize;
    private int rsize;

    public Tree(E data, Tree<E> parent) {
        this.parent = parent;
        val = data;
        lsize = 0;
        rsize = 0;
    }

    public Tree(E data) {
        this(data, null);
    }

    public Tree() { }

    public Tree(Tree<E> tree) {
        this.addAll(tree);
    }

    public E value() {
        return val;
    }

    public Tree<E> getParent() {
        return parent;
    }

    public Tree<E> getLeft() {
        return left;
    }

    public Tree<E> getRight() {
        return right;
    }

    public int getLsize() {
        return lsize;
    }

    public int getRsize() {
        return rsize;
    }

    public void set(E e) {
        if (e == null) throw new NullPointerException();
        val = e;
    }

    @Override
    public int size() {
        return lsize + rsize + (val == null ? 0 : 1);
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public boolean contains(Object o) {
        for (E e : this) {
            if (o.equals(e)) return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new TreeIterator(this);
    }

    private class TreeIterator implements Iterator<E> {
        private Queue<Tree<E>> queue;
        private E next;
        private Tree<E> nextTree;

        public TreeIterator(Tree<E> parentTree) {
            next = val;
            queue = new LinkedList<>();
            nextTree = parentTree;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public E next() {
            E res = next;

            if (nextTree.getLsize() > 0) {
                queue.add(nextTree.getLeft());
            }
            if (nextTree.getRsize() > 0) {
                queue.add(nextTree.getRight());
            }

            if (queue.isEmpty()) {
                next = null;
            }
            else {
                nextTree = queue.remove();
                next = nextTree.value();
            }

            return res;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size()];

        int i = 0;
        for (E e : this) {
            arr[i] = e;
            i++;
        }

        return arr;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size()) {
            return (T[]) toArray();
        }
        else {
            int i = 0;
            for (E e: this) {
                a[i] = (T) e;
                i++;
            }
        }

        if (a.length > size()) a[size()] = null;

        return a;
    }


    @Override
    public boolean add(E e) {
        //A null element does not alter the tree.
        if (e == null) return false;

        if (val == null) {
            //If the tree is empty, the head of the tree is added.
            val = e;
        } else {
            if (lsize <= rsize) {
                if (lsize == 0) {
                    left = new Tree<E>(e, this);
                }
                else {
                    left.add(e);
                }
                lsize++;
            }
            else {
                if (rsize == 0) {
                    right = new Tree<E>(e, this);
                }
                else {
                    right.add(e);
                }
                rsize++;
            }
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) throw new NullPointerException();

        Queue<Tree<E>> queue = new LinkedList<>();


        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean flag = false;
        for (E e : c) {
            if (e == null) throw new NullPointerException();

            add(e);
            flag = true;
        }

        return flag;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
