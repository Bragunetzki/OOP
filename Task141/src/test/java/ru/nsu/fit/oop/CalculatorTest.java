package ru.nsu.fit.oop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private static Stream<Arguments> testInputs() {
        return Stream.of(
                Arguments.of("- 7 - 5 sqrt + 19 17", 8),
                Arguments.of("sqrt 4", 2),
                Arguments.of("pow 2 6", 64),
                Arguments.of("log 1", 0),
                Arguments.of("sin 1", Math.sin(1)),
                Arguments.of("cos 1", Math.cos(1)),
                Arguments.of("/ 5 2", 2.5),
                Arguments.of("* 5 4", 20)
        );
    }

    @ParameterizedTest
    @MethodSource("testInputs")
    public void testCalculator(String input, double res) {
        Calculator calc = new Calculator();
        assertEquals(calc.calculate(input), res);
    }

    @Test
    public void testCalculatorAddOperation() {
        Calculator calc = new Calculator();
        BinaryOperation myaddition = new Sum(calc);
        calc.addOperation("plus", myaddition);
        String input = "plus 7 -4";

        assertEquals(calc.calculate(input), 3);
    }

    @Test
    public void testCalculatorInvalidInput() {
        Calculator calc = new Calculator();
        assertThrows(IllegalArgumentException.class, ()->calc.calculate("wrong operation"));
    }
}