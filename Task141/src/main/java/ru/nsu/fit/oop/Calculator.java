package ru.nsu.fit.oop;

import java.io.InputStream;
import java.util.*;
import java.util.regex.Pattern;

public class Calculator {
    public static float parse(String input) {
        Scanner parser = new Scanner(input);

        Stack<Character> operators = new Stack<>();
        Queue<Float> operands = new ArrayDeque<>();


        //pick apart input into tokens
        while (parser.hasNext()) {
            if (parser.hasNextFloat()) {
                operands.add(parser.nextFloat());
            }
            else {
                Character token = parser.findInLine(". ").charAt(0);
                operators.push(token);
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

    private static float sum(float a, float b) {
        return a+b;
    }

    private static float diff(float a, float b) {
        return a-b;
    }

    private static float mult(float a, float b) {
        return a*b;
    }

    private static float div(float a, float b) {
        return a/b;
    }
}
