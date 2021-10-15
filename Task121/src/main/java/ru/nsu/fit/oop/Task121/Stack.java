package ru.nsu.fit.oop.Task121;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Represents an array that functions like a stack data structure.
 *
 * @param <E> - type of element that will be stored within stack.
 */
public class Stack<E> {
    private Object[] stackArr;
    private int stackSize;
    private int stackCap;

    /**
     * Initializes an empty stack.
     *
     * @param size - initial length of stack.
     */
    public Stack(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Stack size must be at least 1!");
        }

        this.stackSize = 0;
        this.stackCap = size;
        this.stackArr = new Object[stackSize];
    }

    /**
     * Initializes a stack by taking an existing array as a base.
     *
     * @param arr - This array is copied into the stack.
     */
    public Stack(E[] arr) {
        if (arr.length < 1) {
            throw new IllegalArgumentException("Array size must be at least 1!");
        }

        this.stackSize = arr.length;
        this.stackCap = arr.length;
        this.stackArr = new Object[arr.length];

        System.arraycopy(arr, 0, this.stackArr, 0, arr.length);
    }

    /**
     * Checks if stack is full and resizes it if more space is needed.
     */
    private void resize() {
        if (stackCap <= stackSize) {
            stackArr = Arrays.copyOf(stackArr, stackCap * 2);
            stackCap *= 2;
        }
    }

    /**
     * Pushes an element to the end of the stack.
     *
     * @param elem - the element that needs to be pushed.
     */
    public void push(E elem) {
        if (elem == null) throw new IllegalArgumentException("Cannot push null element to stack!");

        stackSize++;
        resize();
        stackArr[stackSize - 1] = elem;
    }

    /**
     * Removes the last element of the stack.
     *
     * @return returns the value of the removed element.
     */
    public E pop() {
        if (stackSize == 0) throw new EmptyStackException();

        E poppedElem = (E) stackArr[stackSize - 1];
        stackArr[stackSize - 1] = null;
        stackSize--;
        return poppedElem;
    }

    /**
     * Pushes an array to the stack.
     *
     * @param pushedArr - array containing variables that need to be pushed.
     */
    public void pushStack(E[] pushedArr) {

        for (int i = 0; i < pushedArr.length; i++) {
            push(pushedArr[i]);
        }
    }

    /**
     * Removes multiple elements from the top of the stack.
     *
     * @param elemNum - number of elements that need to be removed.
     */
    public void popStack(int elemNum) {
        for (int i = 0; i < elemNum; i++) {
            pop();
        }
    }

    public E[] getStackArr() {
        return (E[]) stackArr;
    }

    /**
     * @return - returns the amount of non-empty elements in the stack.
     */
    public int count() {
        return stackSize;
    }
}

