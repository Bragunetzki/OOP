package ru.nsu.fit.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {
    @Test
    public void TreeTestSize() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(5);
        tree.add(5);
        tree.add(4);
        tree.add(4);
        tree.add(3);
        tree.add(2);
        tree.add(-8);

        for (Integer i : tree) System.out.println(i);

        assertEquals(5, tree.size());
    }

    @Test
    public void TreeTestEmpty() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assertTrue(tree.isEmpty());
    }

    @Test
    public void TreeTestAddNull() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assertFalse(tree.add(null));
        tree.add(5);
        assertFalse(tree.add(5));
    }

    @Test
    public  void TreeTestContains() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));
        tree.addAll(Arrays.asList(5, 4, 3, 2, 1));

        assertTrue(tree.containsAll(list));
        assertFalse(tree.contains(10));
    }



}