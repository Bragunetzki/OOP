package ru.nsu.fit.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {
    @Test
    public void treeTestStreamAPI() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.addAll(Arrays.asList(7, 3, 5, 4, 9, 13, 6));

        Integer sumOdd = tree.stream().filter(o -> o % 2 != 0).reduce(Integer::sum).orElse(0);
        Integer sumEven = tree.stream().filter(o -> o % 2 == 0).reduce(Integer::sum).orElse(0);

        assertEquals(sumOdd,37);
        assertEquals(sumEven, 10);
    }

    @Test
    public void treeTestStreamAPIStrings() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.addAll(Arrays.asList("abababa", "ab", "bab", "ba", "123", "hello, there"));
        Object[] expected = new String[] {"ab", "abababa", "bab"};

        Stream<String> stream = tree.stream().filter(str -> str.contains("ab"));

        Object[] arr = stream.toArray();

        assertArrayEquals(arr, expected);
    }

    @Test
    public void treeTestSize() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(5);
        tree.add(5);
        tree.add(4);
        tree.add(4);
        tree.add(3);
        tree.add(2);
        tree.add(-8);

        assertEquals(5, tree.size());
    }

    @Test
    public void treeTestEmpty() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assertTrue(tree.isEmpty());
    }

    @Test
    public void treeTestAddNull() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assertFalse(tree.add(null));
        tree.add(5);
        assertFalse(tree.add(5));
    }

    @Test
    public void treeTestContains() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));
        tree.addAll(Arrays.asList(5, 4, 3, 2, 1));

        assertTrue(tree.containsAll(list));
        assertFalse(tree.contains(10));
    }

    @Test
    public void treeTestToArray() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Integer[] arr1 = new Integer[]{1, 2, 3, 4, 5};
        tree.addAll(Arrays.asList(arr1));

        Integer[] arr2 = tree.toArray(new Integer[tree.size()]);

        assertArrayEquals(arr1, arr2);
    }

    @Test
    public void treeTestClear() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.addAll(Arrays.asList(1, 2, 3, 4, 5));
        assertEquals(5, tree.size());
        tree.clear();
        assertEquals(0, tree.size());
    }

    @Test
    public void treeTestRemove() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.addAll(Arrays.asList(1, 2, 3, 4, 5));
        tree.remove(5);

        Integer[] arr2 = tree.toArray(new Integer[tree.size()]);

        assertArrayEquals(arr2, new Integer[] {1, 2, 3, 4});
    }

    @Test
    public void treeTestRemoveAll() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Integer[] arr1 = new Integer[]{1, 2, 3, 4, 5};
        tree.addAll(Arrays.asList(arr1));

        tree.removeAll(Arrays.asList(1, 2, 3));
        Integer[] arr2 = tree.toArray(new Integer[tree.size()]);

        assertArrayEquals(arr2, new Integer[] {4, 5});
    }

    @Test
    public void treeTestRetainAll() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Integer[] arr1 = new Integer[]{1, 2, 3, 4, 5};
        tree.addAll(Arrays.asList(arr1));

        tree.retainAll(Arrays.asList(1, 2, 3));
        Integer[] arr2 = tree.toArray(new Integer[tree.size()]);

        assertArrayEquals(arr2, new Integer[] {1, 2, 3});
    }
}
