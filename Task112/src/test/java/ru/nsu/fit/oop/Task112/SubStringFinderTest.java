package ru.nsu.fit.oop.Task112;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SubStringFinderTest {
    @Test
    public void testSubStringFinderBasic() {
        int[] res = {0, 2, 4};
        String file = "input.txt";
        String subString = "aba";

        List<Integer> list = SubStringFinder.findSubString(file, subString);

        int[] result = list.stream().mapToInt(i->i).toArray();

        assertArrayEquals(res, result);
    }

}