package ru.nsu.fit.oop;

import java.io.InputStream;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Implements a simple calculator that parses a string in prefix notation.
 */
public class Calculator {
    /**
     * Parses a valid prefix notation string and returns the result.
     *
     * @param input - the string that needs to be parsed.
     * @return - returns a result of calculations as double.
     */
    public static double calculate(String input) {
        Scanner parser = new Scanner(input);

        Stack<Character> operators = new Stack<>();
        Stack<Integer> arities = new Stack<>();

        Stack<Double> operands = new Stack<>();
        Stack<Integer> operandGroups = new Stack<>();

        int lastGroup = 0;

        while (parser.hasNext()) {

            if (parser.hasNextDouble()) {
                operands.push(parser.nextDouble());
                lastGroup++;
            } else if (parser.hasNext(".")) {
                Character token = parser.findInLine(". ").charAt(0);
                operators.push(token);
                arities.push(2);
                operandGroups.push(lastGroup);
                lastGroup = 0;
            } else if (parser.hasNext("log")) {
                parser.next("log");
                operators.push('l');
                arities.push(1);
                operandGroups.push(lastGroup);
                lastGroup = 0;
            } else if (parser.hasNext("pow")) {
                parser.next("pow");
                operators.push('p');
                arities.push(2);
                operandGroups.push(lastGroup);
                lastGroup = 0;
            } else if (parser.hasNext("sqrt")) {
                parser.next("sqrt");
                operators.push('r');
                arities.push(1);
                operandGroups.push(lastGroup);
                lastGroup = 0;
            } else if (parser.hasNext("sin")) {
                parser.next("sin");
                operators.push('s');
                arities.push(1);
                operandGroups.push(lastGroup);
                lastGroup = 0;
            } else if (parser.hasNext("cos")) {
                parser.next("cos");
                operators.push('c');
                arities.push(1);
                operandGroups.push(lastGroup);
                lastGroup = 0;
            }

            // System.out.printf("top arity is %d and top groupsize is %d\n", arities.peek(), lastGroup);
            while (arities.peek() == lastGroup) {
                double operand1 = operands.pop();
                double operand2;
                double res = 0;

                switch (operators.pop()) {
                    case ('+') -> {
                        operand2 = operands.pop();
                        res = operand2 + operand1;
                    }
                    case ('-') -> {
                        operand2 = operands.pop();
                        res = operand2 - operand1;
                    }
                    case ('*') -> {
                        operand2 = operands.pop();
                        res = operand2 * operand1;
                    }
                    case ('/') -> {
                        operand2 = operands.pop();
                        res = operand2 / operand1;
                    }
                    case ('l') -> {
                        res = Math.log(operand1);
                    }
                    case ('p') -> {
                        operand2 = operands.pop();
                        res = Math.pow(operand2, operand1);
                    }
                    case ('r') -> {
                        res = Math.sqrt(operand1);
                    }
                    case ('s') -> {
                        res = Math.sin(operand1);
                    }
                    case ('c') -> {
                        res = Math.cos(operand1);
                    }
                }

                operands.push(res);
                lastGroup = operandGroups.pop() + 1;
                arities.pop();

                if (arities.empty()) break;
            }
        }

        parser.close();

        return operands.pop();
    }
}
