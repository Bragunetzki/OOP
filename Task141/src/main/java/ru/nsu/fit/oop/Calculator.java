package ru.nsu.fit.oop;

import java.io.InputStream;
import java.util.*;
import java.lang.reflect.*;

/**
 * Implements a simple calculator that parses a string in prefix notation.
 */
abstract class Calculator {
    private static final Map<String, Operation> operationMap;
    static {
        Map<String, Operation> aMap = new HashMap<String, Operation>();
        aMap.put("+", new Sum());
        aMap.put("-", new Diff());
        aMap.put("*", new Mult());
        aMap.put("/", new Div());
        aMap.put("log", new Log());
        aMap.put("sqrt", new Sqrt());
        aMap.put("sin", new Sin());
        aMap.put("pow", new Pow());
        aMap.put("sin", new Sin());
        aMap.put("cos", new Cos());

        operationMap = Collections.unmodifiableMap(aMap);
    }

    /**
     * Selects an operation depending on the provided token.
     * @param operation the token that determines the operation.
     * @return returns an instance of the selected operation.
     */
    static Operation callOperation(String operation) {
       return operationMap.get(operation);
    }

    /**
     * Calculates the result of a valid expression in polish notation.
     * @param input - the string that contains the expression.
     * @return - returns the result of the calculation as a double.
     */
    public static double calculate(String input) {
        Scanner parser = new Scanner(input);

        if (parser.hasNextDouble()) {
            return parser.nextDouble();
        }

        String operation = parser.next();
        Operation op = callOperation(operation);
        return op.calculate(parser);
    }
}