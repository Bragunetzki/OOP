package ru.nsu.fit.oop.Task121;

import java.util.Arrays;

/**
 * Represents an array that functions like a stack data structure.
 */
public class Stack {
    private int[] stackArr;
    private int stackSize;
    private int stackCap;

    /**
     * Initializes an empty stack.
     * @param size - initial length of stack.
     */
    public Stack(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Stack size must be at least 1!");
        }

        this.stackSize = 0;
        this.stackCap = size;
        this.stackArr = new int[stackSize];
    }

    /**
     * Initializes a stack by taking an existing array as a base.
     * @param arr - This array is copied into the stack.
     */
    public Stack(int[] arr) {
        if (arr.length < 1) {
            throw new IllegalArgumentException("Array size must be at least 1!");
        }

        this.stackSize = arr.length;
        this.stackCap = arr.length;
        this.stackArr = new int[arr.length];

        System.arraycopy(arr, 0, this.stackArr, 0, arr.length);
    }

    /**
     * Checks if stack is full and resizes it if more space is needed.
     */
    private void resize() {
        if (stackCap <= stackSize) {
            stackArr = Arrays.copyOf(stackArr, stackCap*2);
            stackCap*=2;
        }
    }

    /**
     * @return returns the array which is used for the stack data.
     */
    public int[] getStackArr() {
        return stackArr;
    }

    /**
     * Pushes an element to the end of the stack.
     * @param elem - the element that needs to be pushed.
     */
    public void push(int elem) {
        stackSize++;
        resize();
        stackArr[stackSize-1]=elem;
    }

    /**
     * Removes the last element of the stack.
     * @return returns the value of the removed element.
     */
    public int pop() {
        int poppedElem = stackArr[stackSize-1];
        stackArr[stackSize-1]=0;
        stackSize--;
        return poppedElem;
    }

    /**
     * Pushes an array to the stack.
     * @param pushedArr - array containing variables that need to be pushed.
     */
    public void pushStack(int[] pushedArr) {
        for (int i = 0; i < pushedArr.length; i++) {
            push(pushedArr[i]);
        }
    }

    /**
     * Removes multiple elements from the top of the stack.
     * @param elemNum - number of elements that need to be removed.
     */
    public void popStack(int elemNum) {
        for (int i = 0; i < elemNum; i++) {
            pop();
        }
    }

    /**
     * @return - returns the amount of non-empty elements in the stack.
     */
    public int count() {
        return stackSize;
    }
}

