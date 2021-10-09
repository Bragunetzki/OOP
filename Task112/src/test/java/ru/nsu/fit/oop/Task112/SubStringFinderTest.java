package ru.nsu.fit.oop.Task112;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SubStringFinderTest {

    private static Stream<Arguments> testArrays() {
        return Stream.of(
                Arguments.of("aba", "abababab", new int[]{0, 2, 4}),
                Arguments.of("cake", "I want cake!", new int[]{7}),
                Arguments.of("hello", "I want cake!", new int[]{}),
                Arguments.of("l", "lllll", new int[]{0, 1, 2, 3, 4})
        );
    }

    @ParameterizedTest
    @MethodSource("testArrays")
    public void testSubStringFinder(String needle, String haystack, int[] res) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("input.txt"));
        writer.write(haystack, 0, haystack.length());
        writer.close();

        List<Integer> list = SubStringFinder.findStringInFile("input.txt", needle);
        int[] result = list.stream().mapToInt(i -> i).toArray();

        assertArrayEquals(res, result);
    }

    @ParameterizedTest
    @NullSource
    public void TestSubStringFinderNull(String input) {
        assertThrows(IllegalArgumentException.class, () -> SubStringFinder.findStringInFile("input.txt", input));
    }

}