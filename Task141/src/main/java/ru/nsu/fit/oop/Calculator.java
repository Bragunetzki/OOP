package ru.nsu.fit.oop;

import org.junit.jupiter.api.extension.ParameterResolutionException;

import java.text.ParseException;
import java.util.*;

/**
 * Implements a simple calculator that parses a string in prefix notation.
 */
public class Calculator {
    private Map<String, Operation> operationMap;

    /**
     * Calculator constructor that initializes the inner operationMap.
     */
    public Calculator() {
        operationMap = new HashMap<String, Operation>();
        operationMap.put("+", new Sum(this));
        operationMap.put("-", new Sub(this));
        operationMap.put("*", new Mult(this));
        operationMap.put("/", new Div(this));
        operationMap.put("log", new Log(this));
        operationMap.put("sqrt", new Sqrt(this));
        operationMap.put("sin", new Sin(this));
        operationMap.put("pow", new Pow(this));
        operationMap.put("cos", new Cos(this));
    }

    /**
     * Adds a new operation to the Calculator.
     * @param key - the string key that is mapped to this operation for parsing.
     * @param newOperation - the added operation.
     */
    public void addOperation(String key, Operation newOperation) {
        operationMap.put(key, newOperation);
    }

    /**
     * Selects an operation depending on the provided token.
     * @param operation the token that determines the operation.
     * @return returns an instance of the selected operation.
     */
    Operation getOperation(String operation) {
        if (!operationMap.containsKey(operation)) throw new IllegalArgumentException("Unknown operation.");
        return operationMap.get(operation);
    }

    /**
     * Calculates the result of a valid expression in polish notation.
     * @param input - the string that contains the expression.
     * @return - returns the result of the calculation as a double.
     */
    public double calculate(String input) {
        Scanner parser = new Scanner(input);

        if (parser.hasNextDouble()) {
            return parser.nextDouble();
        }

        String operation = parser.next();
        Operation op = getOperation(operation);
        return op.calculate(parser);
    }
}