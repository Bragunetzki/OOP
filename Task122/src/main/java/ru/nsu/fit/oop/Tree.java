package ru.nsu.fit.oop;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class Tree<E> implements Collection<E> {
    private E val;
    private Tree<E> parent;
    private List<Tree<E>> children;
    private int size;

    public Tree(E data) {
        val = data;
        children = new ArrayList<Tree<E>>();
    }

    public Tree() {
        children = new ArrayList<Tree<E>>():
    }

    public Tree(Tree<E> tree) {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new TreeIterator();
    }

    @Override
    public Spliterator<E> spliterator() {
        return new TreeSpliterator();
    }

    public class TreeSpliterator implements Spliterator<E> {

        @Override
        public boolean tryAdvance(Consumer<? super E> action) {
            return false;
        }

        @Override
        public Spliterator<E> trySplit() {
            return null;
        }

        @Override
        public long estimateSize() {
            return 0;
        }

        @Override
        public int characteristics() {
            return 0;
        }
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        //A null element does not alter the tree.
        if (e == null) return false;

        if (val == null) {
            //If the tree is empty, the head of the tree is added.
            val = e;
        } else if (children.isEmpty()) {
            //If the tree has no children, add the first one.
            children.add(new Tree<E>(e));
            children.get(0).parent = this;
        }
        else {
            //If the tree has children, randomly decide between
            //adding a new child to the tree or one of its subtrees.
            int whereto = ThreadLocalRandom.current().nextInt(-1, children.size());
            if (whereto == -1) {
                //Add a new child to the tree.
                children.add(new Tree<E>(e));
                children.get(children.size()-1).parent = this;
            }
            else {
                //recursively add the child to one of the children.
                children.get(whereto).add(e);
            }
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
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
