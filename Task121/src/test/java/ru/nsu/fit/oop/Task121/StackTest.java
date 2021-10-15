package ru.nsu.fit.oop.Task121;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    @Test
    public void testStack1() {
        Integer[] res = {2, null};
        Stack<Integer> testedStack = new Stack<Integer>(1);
        testedStack.push(2);
        assertArrayEquals(res, testedStack.getStackArr());

        testedStack.push(7);
        res = new Integer[] {2, 7, null, null};
        assertArrayEquals(res, testedStack.getStackArr());

        Integer[] pushedArr = {4, 8};
        testedStack.pushStack(pushedArr);
        res = new Integer[] {2, 7, 4, 8, null, null, null, null};
        assertArrayEquals(res, testedStack.getStackArr());

        testedStack.pop();
        res = new Integer[] {2, 7, 4, null, null, null, null, null};
        assertArrayEquals(res, testedStack.getStackArr());

        testedStack.popStack(2);
        res = new Integer[] {2, null, null, null, null, null, null, null};
        assertArrayEquals(res, testedStack.getStackArr());

        assertEquals(1,testedStack.count());
    }

}