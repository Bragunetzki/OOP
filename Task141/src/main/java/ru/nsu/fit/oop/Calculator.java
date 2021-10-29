package ru.nsu.fit.oop;

import java.io.InputStream;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Implements a simple calculator that parses a string in prefix notation.
 */
public class Calculator {
    /**
     * Parses a string in prefix notation and returns the result.
     * @param input - the string that needs to be parsed.
     * @return - returns a result of calculations as float.
     */
    public static float parse(String input) {
        Scanner parser = new Scanner(input);

        Stack<Character> operators = new Stack<>();
        Queue<Float> operands = new ArrayDeque<>();


        //pick apart input into tokens
        while (parser.hasNext()) {
            if (parser.hasNextFloat()) {
                operands.add(parser.nextFloat());
            }
            else if (parser.hasNext(". ")) {
                Character token = parser.findInLine(". ").charAt(0);
                operators.push(token);
            }
            else if (parser.hasNext("log ")) {
                operators.push('l');
            }
            else if (parser.hasNext("pow ")) {
                operators.push('p');
            }
            else if (parser.hasNext("sqrt ")) {
                operators.push('r');
            }
            else if (parser.hasNext("sin ")) {
                operators.push('s');
            }
            else if (parser.hasNext("cos")) {
                operators.push('c');
            }
        }

        //pop tokens out of stacks to finish calculation
        float res = operands.remove();

        while (!operators.empty()) {
            switch (operators.pop()) {
                case ('+') -> res = sum(res, operands.remove());
                case ('-') -> res = diff(res, operands.remove());
                case ('*') -> res = mult(res, operands.remove());
                case ('/') -> res = div(res, operands.remove());
                default -> {
                }
            }
        }

        parser.close();

        return res;
    }

    /**
     * Calculates sum of two operands.
     * @param a - operand a.
     * @param b - operand b.
     * @return - returns sum of the two operands as float value.
     */
    private static float sum(float a, float b) {
        return a+b;
    }

    /**
     * Calculates difference of two operands.
     * @param a - operand a.
     * @param b - operand b.
     * @return - returns difference of two operands as float value.
     */
    private static float diff(float a, float b) {
        return a-b;
    }

    /**
     * Calculates the product of two operands.
     * @param a - operand a.
     * @param b - operand b.
     * @return - returns product of two operands as float value.
     */
    private static float mult(float a, float b) {
        return a*b;
    }

    /**
     * Calculates the quotient of two operands.
     * @param a - operand a.
     * @param b - operand b.
     * @return - returns the quotient of two operands as float value.
     */
    private static float div(float a, float b) {
        return a/b;
    }

    /**
     * Calculates the log of an operand.
     * @param a - operand to find log of.
     * @return - returns the log as float value.
     */
    private static float log(float a) {
        return (float) Math.log(a);
    }

    /**
     * Calculates the value of operand 'a' to the power of operand 'b'.
     * @param a - operand a.
     * @param b - operand b.
     * @return - returns the power as float value.
     */
    private static float pow(float a, float b) {
        return (float) Math.pow(a, b);
    }

    /**
     * Calculates the square root of an operand.
     * @param a - operand to find square root of.
     * @return - returns the square root as float value.
     */
    private static float sqrt(float a) {
        return (float) Math.sqrt(a);
    }

    /**
     * Calculates the sine of an operand.
     * @param a - operand to find sine of.
     * @return - returns the sine as float value.
     */
    private static float sin(float a) {
        return (float) Math.sin(a);
    }

    /**
     * Calculates the cosine of an operand.
     * @param a - operand to find cosine of.
     * @return - returns the cosine as float value.
     */
    private static float cos(float a) {
        return (float) Math.cos(a);
    }
}
