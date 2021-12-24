package ru.nsu.fit.oop;

import java.util.*;

public class BinarySearchTree<E extends Comparable<E>> implements Collection<E> {
    private E val;
    private BinarySearchTree<E> parent, left, right;

    private int lsize;
    private int rsize;

    public BinarySearchTree(E data, BinarySearchTree<E> parent) {
        this.parent = parent;
        val = data;
        lsize = 0;
        rsize = 0;
    }

    public BinarySearchTree(E data) {
        this(data, null);
    }

    public BinarySearchTree() {
    }

    public BinarySearchTree(BinarySearchTree<E> tree) {
        this.addAll(tree);
    }

    public E value() {
        return val;
    }

    public BinarySearchTree<E> getParent() {
        return parent;
    }

    public BinarySearchTree<E> getLeft() {
        return left;
    }

    public BinarySearchTree<E> getRight() {
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
        private E next;
        private BinarySearchTree<E> nextTree;

        public TreeIterator(BinarySearchTree<E> parentTree) {
            nextTree = parentTree;

            while (nextTree.getLeft() != null) {
                nextTree = nextTree.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return nextTree != null;
        }

        @Override
        public E next() {
            E res = nextTree.value();

            if (nextTree.right != null) {
                nextTree = nextTree.right;
                while (nextTree.left != null) {
                    nextTree = nextTree.left;
                }
                return res;
            }

            while (true) {
                if (nextTree.parent == null) {
                    nextTree = null;
                    return res;
                }
                if (nextTree.parent.left.equals(nextTree)) {
                    nextTree = nextTree.parent;
                    return res;
                }
                nextTree = nextTree.parent;
            }
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
        } else {
            int i = 0;
            for (E e : this) {
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
            if (val.compareTo(e) == 0) return false;

            if (val.compareTo(e) > 0) {
                if (lsize == 0) {
                    left = new BinarySearchTree<>(e, this);
                    lsize++;
                } else {
                    if (left.add(e)) lsize++;
                }
            } else {
                if (rsize == 0) {
                    right = new BinarySearchTree<>(e, this);
                    rsize++;
                } else {
                    if (right.add(e)) rsize++;
                }
            }
        }

        return true;
    }

    private void reduceParentSize(BinarySearchTree<E> tree) {
        while (tree.parent != null) {
            if (tree.parent.left.equals(tree)) {
                tree.parent.lsize--;
            } else {
                tree.parent.rsize--;
            }

            reduceParentSize(tree.parent);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        //a null element deletion does not modify the tree.
        if (o == null) return false;

        BinarySearchTree<E> removedTree = findSubTree((E) o);
        return removeNode(removedTree);
    }

    private boolean removeNode(BinarySearchTree<E> removedTree) {
        if (removedTree == null) return false;
        BinarySearchTree<E> parent = removedTree.parent;

        //leaf needs to be removed;
        if (removedTree.rsize == 0 && removedTree.lsize == 0) {
            if (parent == null) removedTree.val = null;

            if (parent.left.equals(removedTree)) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            reduceParentSize(removedTree);


            return true;
        }

        //node has just one child
        if (removedTree.rsize + removedTree.lsize == 1) {
            if (removedTree.lsize == 1) {
                removedTree.val = removedTree.left.val;
                removedTree.lsize = 0;
                removedTree.left = null;
            } else {
                removedTree.val = removedTree.right.val;
                removedTree.rsize = 0;
                removedTree.right = null;
            }

            reduceParentSize(removedTree);

            return true;
        }


        //node has 2 or more children.
        //find next successor node, copy its value and remove the successor node.
        BinarySearchTree<E> succ = findSuccessor(removedTree.val);
        removedTree.val = succ.val;
        removeNode(succ);

        return true;
    }


    public BinarySearchTree<E> findSuccessor(E e) {
        if (e.compareTo(val) < 0) {
            if (lsize == 0) return this;
            BinarySearchTree<E> res = left.findSuccessor(e);
            if (res == null) return this;
            else return res;
        } else {
            if (rsize == 0) return null;
            return right.findSuccessor(e);
        }
    }

    private BinarySearchTree<E> findSubTree(E e) {

        if (val.compareTo(e) == 0) return this;

        if (val.compareTo(e) > 0) {
            if (lsize == 0) return null;
            return left.findSubTree(e);
        } else {
            if (rsize == 0) return null;
            return right.findSubTree(e);
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!c.contains(o)) {
                return false;
            }
        }
        return true;
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
        boolean flag = true;
        for (Object o : c) {
            if (!remove(o)) flag = false;
        }

        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        ArrayList<E> removeList = new ArrayList<>();
        for (E e: this) {
            if (!c.contains(e)) removeList.add(e);
        }

        for (E e : removeList) {
            remove(e);
        }

        return (!removeList.isEmpty());
    }

    @Override
    public void clear() {
        ArrayList<E> clearList = new ArrayList<>(this);

        for (E e : clearList) {
            remove(e);
        }
    }
}
