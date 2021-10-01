package ru.nsu.fit.oop.Task111;
/**
 * Contains functions for running heap sort on array.
 */
public class HeapSort {

    /**
     * Swaps to elems of array.
     * @param arr - input array
     * @param a - index of first element
     * @param b - index of second element
     */
    private static void swap(int[] arr, int a, int b) {
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    /**
     * Builds heap out of array.
     * @param arr - input array.
     * @param n - last element number.
     * @param start - first element number.
     */
    private static void heapify(int[] arr, int n, int start) {
        int max = start;
        int l = 2*start+1;
        int r = 2*start+2;

        if (l < n && arr[max] < arr[l])
            max = l;

        if (r < n && arr[max] < arr[r])
            max = r;

        if (max != start) {
            swap(arr, start, max);

            heapify(arr, n, max);
        }
    }

    /**
     * Sorts the array.
     * @param arr - input array.
     */
    public static void sort(int[] arr) {

        if (arr == null) {
            throw new IllegalArgumentException("Null argument exception!");
        }

        int n = arr.length;

        //build initial heap
        for (int i = n/2-1; i>=0; i--)
            heapify(arr, n, i);

        //get elements from heap one by one
        for (int i = n-1; i > 0; i--) {
            //swap the root with the last element
            swap(arr, 0, i);

            //build heap out of reduced array
            heapify(arr, i, 0);
        }
    }
}