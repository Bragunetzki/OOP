package ru.nsu.fit.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {
    @Test
    public void TreeTest1() {
        Tree<Integer> tree = new Tree<>(5);
        tree.add(5);
        tree.add(4);
        tree.add(3);
        tree.add(2);
        tree.add(-8);

        for (Integer i : tree) {
            System.out.println(i);
        }

        assertTrue(true);
    }

}