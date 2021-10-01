package ru.nsu.fit.oop.Task111;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {
    static ArrayList<int[]> testArrays() {
        var list = new ArrayList<int[]>();

        list.add(new int[] {0, 0, 0, 0, 0});
        list.add(new int[] {5, 4, 3, 2, 1});
        list.add(new int[] {1});
        list.add(new int[] {2, 1});
        list.add(new int[] {-4, 8, -3, 6, -2, 4, -1, 2});
        list.add(new int[] {1, 2, 7, 3, 4, 2, 9});
        list.add(new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE});
        list.add(new int[] {});

        return list;
    }

    @ParameterizedTest
    @MethodSource("testArrays")
    public void testHeapSortBasic(int[] arr) {
        int[] res = arr.clone();
        Arrays.sort(res);
        HeapSort.sort(arr);
        assertArrayEquals(res, arr);
    }

    @ParameterizedTest
    @NullSource
    void testNull(int[] input) {
        assertThrows(IllegalArgumentException.class, ()->HeapSort.sort(input));
    }

}