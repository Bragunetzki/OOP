package ru.nsu.fit.oop.Task_1_1_1.Task_1_1_1;
/**
 * contains functions for running heap sort on array
 */
public class HeapSort {

    /**
     * builds heap out of array
     * @param arr - input array
     * @param n - last element number
     * @param start - first element number
     */
    public static void heapify(int[] arr, int n, int start) {
        int max = start;
        int l = 2*start+1;
        int r = 2*start+2;

        if (l < n && arr[max] < arr[l])
            max = l;

        if (r < n && arr[max] < arr[r])
            max = r;

        if (max != start) {
            int t = arr[start];
            arr[start] = arr[max];
            arr[max] = t;

            heapify(arr, n, max);
        }
    }

    /**
     * sorts the array
     * @param arr - input array
     */
    public static void sort(int[] arr) {
        int n = arr.length;

        //build initial heap
        for (int i = n/2-1; i>=0; i--)
            heapify(arr, n, i);

        //get elements from heap one by one
        for (int i = n-1; i > 0; i--) {
            //swap the root with the last element
            int t = arr[0];
            arr[0] = arr[i];
            arr[i] = t;

            //build heap out of reduced array
            heapify(arr, i, 0);
        }
    }
}

