package ru.nsu.fit.oop;

import java.util.*;
import java.util.function.Consumer;

/**
 * Represents a binary search tree collection.
 * @param <E> - the type of element stored in the tree.
 */
public class BinarySearchTree<E extends Comparable<E>> implements Collection<E> {
    private E val;
    private BinarySearchTree<E> parent, left, right;

    private int lsize;
    private int rsize;

    /**
     * Constructs a tree with a given parent and data.
     * @param data - data that is stored at the root of the new tree.
     * @param parent - the new tree's parent tree.
     */
    public BinarySearchTree(E data, BinarySearchTree<E> parent) {
        this.parent = parent;
        val = data;
        lsize = 0;
        rsize = 0;
    }

    /**
     * Constructs a new tree without a parent.
     * @param data - data that is stored at the root of the new tree.
     */
    public BinarySearchTree(E data) {
        this(data, null);
    }

    /**
     * Constructs an empty tree.
     */
    public BinarySearchTree() {
    }

    /**
     * Constructs a tree that copies all elements of another tree.
     * @param tree - the tree that should be copied.
     */
    public BinarySearchTree(BinarySearchTree<E> tree) {
        this.addAll(tree);
    }

    /**
     * Returns the value at the root of the tree.
     * @return - value of type E.
     */
    public E value() {
        return val;
    }

    /**
     * Returns the parent of the tree.
     * @return - parent of tree.
     */
    public BinarySearchTree<E> getParent() {
        return parent;
    }

    /**
     * Returns the left child of the tree.
     * @return - left child of tree.
     */
    public BinarySearchTree<E> getLeft() {
        return left;
    }

    /**
     * Returns the right child of the tree.
     * @return - right child of tree.
     */
    public BinarySearchTree<E> getRight() {
        return right;
    }

    /**
     * Returns number of left children of tree.
     * @return - size of left subtree.
     */
    public int getLsize() {
        return lsize;
    }

    /**
     * Returns number of right children of tree.
     * @return  - size of right subtree.
     */
    public int getRsize() {
        return rsize;
    }

    /**
     * Sets the value at the root of the tree to the given value.
     * @param e - the value that should be set.
     */
    public void set(E e) {
        if (e == null) throw new NullPointerException();
        val = e;
    }

    /**
     * Returns the size of the tree.
     * @return - size of tree.
     */
    @Override
    public int size() {
        return lsize + rsize + (val == null ? 0 : 1);
    }

    /**
     * @return - returns true if tree is empty and false if tree has any elements.
     */
    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    /**
     * Checks if the tree contains a given object.
     * @param o - the object that should be searched for.
     * @return - returns true if the tree contains the object and false otherwise.
     */
    @Override
    public boolean contains(Object o) {
        for (E e : this) {
            if (o.equals(e)) return true;
        }
        return false;
    }

    /**
     * Returns an iterator that can iterate through the tree.
     * @return - returns the iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new TreeIterator(this);
    }

    /**
     * The iterator class that works with this tree.
     */
    private class TreeIterator implements Iterator<E> {
        private E next;
        private BinarySearchTree<E> nextTree;
        private BinarySearchTree<E> last;

        public TreeIterator(BinarySearchTree<E> parentTree) {
            nextTree = minNode();
            last = maxNode();
        }

        @Override
        public boolean hasNext() {
            return nextTree != null;
        }

        @Override
        public E next() {
            E res = nextTree.value();

            if (nextTree == last) {
                nextTree = null;
                return res;
            }

            if (nextTree.right != null) {
                nextTree = nextTree.right.minNode();
            } else if (nextTree.parent != null) {
                BinarySearchTree<E> prev = nextTree;
                nextTree = nextTree.parent;
                while (prev == nextTree.right) {
                    prev = nextTree;
                    nextTree = nextTree.parent;
                }
            }
            return res;
        }
    }

    /**
     * Finds the minimum child of given tree.
     * @return - returns the minimum leaf of tree.
     */
    private BinarySearchTree<E> minNode() {
        BinarySearchTree<E> res = this;
        while (res.left != null) {
            res = res.left;
        }

        return res;
    }

    /**
     * Finds the maximum child of given tree.
     * @return - returns the maximum leaf of tree.
     */
    private BinarySearchTree<E> maxNode() {
        BinarySearchTree<E> res = this;
        while (res.right != null) {
            res = res.right;
        }

        return res;
    }

    /**
     * Returns a spliterator that can iterate through the tree.
     * @return - returns the spliterator.
     */
    @Override
    public Spliterator<E> spliterator() {
        return new TreeSpliterator(this);
    }

    /**
     * The spliterator class that works with this tree.
     */
    private class TreeSpliterator implements Spliterator<E> {
        private E curr_val;
        private BinarySearchTree<E> nextTree;
        private BinarySearchTree<E> last;

        public TreeSpliterator(BinarySearchTree<E> parentTree) {
            nextTree = minNode();
            last = maxNode();
        }

        @Override
        public boolean tryAdvance(Consumer<? super E> action) {
            if (nextTree != null) {
                E res = nextTree.val;

                if (nextTree == last) {
                    action.accept(res);
                    nextTree = null;
                    return true;
                }

                if (nextTree.right != null) {
                    nextTree = nextTree.right.minNode();
                } else if (nextTree.parent != null) {
                    BinarySearchTree<E> prev = nextTree;
                    nextTree = nextTree.parent;
                    while (prev == nextTree.right) {
                        prev = nextTree;
                        nextTree = nextTree.parent;
                    }
                }

                action.accept(res);
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Spliterator<E> trySplit() {
            if (estimateSize() <= 1) return null;

            nextTree = nextTree.left;
            return new TreeSpliterator(nextTree.right);
        }

        @Override
        public long estimateSize() {
            return nextTree.size();
        }

        @Override
        public int characteristics() {
            return ORDERED | DISTINCT | SORTED | SIZED | SUBSIZED;
        }

        @Override
        public Comparator<? super E> getComparator() {
            return null;
        }
    }

    /**
     * Converts tree to array.
     * @return - returns an array containing the ordered elements of the tree.
     */
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

    /**
     * Converts tree to array and attempts to fill the given array with the data.
     * @param a 0 the array that should be filled.
     * @param <T> - the type of element contained in array.
     * @return - returns an array containing the ordered elements of the tree.
     */
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


    /**
     * Adds an element to the tree. The tree is not self-balancing,
     * so one should take care when adding an ordered list of elements.
     * Duplicate elements do not alter the tree.
     * @param e - the element that should be added to the tree.
     * @return - returns true if the tree changed after the element was added, returns false otherwise.
     */
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

    /**
     * Reduces the size of all predecessor trees by 1.
     * @param tree - the tree from which to start.
     */
    private void reduceParentSize(BinarySearchTree<E> tree) {
        while (tree.parent != null) {
            if (tree.parent.left == null) {
                tree.parent.rsize--;
                reduceParentSize(tree.parent);
                return;
            }

            if (tree.parent.left.equals(tree)) {
                tree.parent.lsize--;
            } else {
                tree.parent.rsize--;
            }

            reduceParentSize(tree.parent);
        }
    }

    /**
     * Removes an object from the tree.
     * @param o - the object that should be removed.
     * @return - returns true if the tree was changed after the element was removed, returns false otherwise.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        //a null element deletion does not modify the tree.
        if (o == null) return false;

        BinarySearchTree<E> removedTree = findSubTree((E) o);
        return removeNode(removedTree);
    }

    /**
     * Removes a specific subtree from the tree.
     * @param removedTree - the subtree that should be removed.
     * @return - returns true if the tree was changed after the subtree was removed, returns false otherwise
     */
    private boolean removeNode(BinarySearchTree<E> removedTree) {
        if (removedTree == null) return false;
        BinarySearchTree<E> parent = removedTree.parent;

        //leaf needs to be removed;
        if (removedTree.rsize == 0 && removedTree.lsize == 0) {
            if (parent == null) {
                removedTree.val = null;
                return true;
            }

            if (parent.left == null) {
                parent.right = null;
                reduceParentSize(removedTree);
                return true;
            }

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


    /**
     * Finds the next in-order successor of an element.
     * @param e - the element that should be compared to.
     * @return - returns the subtree containing the successor.
     */
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

    /**
     * Finds a subtree containing the specified element.
     * @param e - the element which the subtree should contain.
     * @return - returns the subtree containing the element.
     */
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

    /**
     * Checks if the tree contains all elements of given collection.
     * @param c - the collection that should be compared to.
     * @return - returns true if the tree contains each element of collection, and returns false otherwise.
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!c.contains(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds all elements of given collection to the tree.
     * @param c - the collection from which to pool elements.
     * @return - returns true if the tree was modified after the addition, returns false otherwise.
     */
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

    /**
     * Removes all elements contained in the collection from the tree.
     * @param c - the collection from which to take elements.
     * @return - returns true if the tree was modified after the addition, returns false otherwise.
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean flag = true;
        for (Object o : c) {
            if (!remove(o)) flag = false;
        }

        return flag;
    }

    /**
     * Removes all elements from the tree, except for those contained in the given collection.
     * @param c - the collection which to compare with.
     * @return - returns true if the tree was modified after the operation, returns false otherwise.
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        ArrayList<E> removeList = new ArrayList<>();
        for (E e : this) {
            if (!c.contains(e)) removeList.add(e);
        }

        for (E e : removeList) {
            remove(e);
        }

        return (!removeList.isEmpty());
    }

    /**
     * Removes all elements from the tree.
     */
    @Override
    public void clear() {
        ArrayList<E> clearList = new ArrayList<>(this);

        for (E e : clearList) {
            remove(e);
        }
    }
}
