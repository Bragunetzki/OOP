package ru.nsu.fit.oop.Task111;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {

    @Test
    public void testHeapSortBasic() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] res = {1, 2, 3, 4, 5};
        HeapSort.sort(arr);
        assertArrayEquals(res, arr);
    }

    @Test
    public void testHeapSortSameElem() {
        int[] arr = {1, 1, 1, 1, 1};
        int[] res = {1, 1, 1, 1, 1};
        HeapSort.sort(arr);
        assertArrayEquals(res, arr);
    }

    @Test
    public void testHeapSortSorted() {
        int[] arr = {1, 2};
        int[] res = {1, 2};
        HeapSort.sort(arr);
        assertArrayEquals(res, arr);
    }

    @Test
    public void testHeapSortTwoElem() {
        int[] arr = {2, 1};
        int[] res = {1, 2};
        HeapSort.sort(arr);
        assertArrayEquals(res, arr);
    }

    @Test
    public void testHeapSortOneElem() {
        int[] arr = {4};
        int[] res = {4};
        HeapSort.sort(arr);
        assertArrayEquals(res, arr);
    }

    @Test
    public void testHeapSortRepeatingElems() {
        int[] arr = {2, 2, 1, 1};
        int[] res = {1, 1, 2, 2};
        HeapSort.sort(arr);
        assertArrayEquals(res, arr);
    }

    @Test
    public void testHeapSortAdvanced() {
        int[] arr = {9, 1, 8, 2, 7, 3, 6, 4, 5};
        int[] res = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        HeapSort.sort(arr);
        assertArrayEquals(res, arr);
    }

}