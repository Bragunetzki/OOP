package ru.nsu.fit.oop.Task112;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubStringFinderTest {

    @Test
    public void testFinderBasic() {
        int[] trueResult = {7};
        List<Integer> list = SubStringFinder.findSubString("input.txt", "пирог");

        int[] result = list.stream().mapToInt(i->i).toArray();

        assertArrayEquals(trueResult, result);
    }

}