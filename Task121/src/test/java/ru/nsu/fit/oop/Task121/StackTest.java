package ru.nsu.fit.oop.Task121;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    @Test
    public void testStack1() {
        int[] res = {2, 0};
        Stack testedStack = new Stack(1);
        testedStack.push(2);
        assertArrayEquals(res, testedStack.getStackArr());

        testedStack.push(7);
        res = new int[] {2, 7, 0, 0};
        assertArrayEquals(res, testedStack.getStackArr());

        int[] pushedArr = {4, 8};
        testedStack.pushStack(pushedArr);
        res = new int[] {2, 7, 4, 8, 0, 0, 0, 0};
        assertArrayEquals(res, testedStack.getStackArr());

        testedStack.pop();
        res = new int[] {2, 7, 4, 0, 0, 0, 0, 0};
        assertArrayEquals(res, testedStack.getStackArr());

        testedStack.popStack(2);
        res = new int[] {2, 0, 0, 0, 0, 0, 0, 0};
        assertArrayEquals(res, testedStack.getStackArr());

        assertEquals(1,testedStack.count());
    }

}