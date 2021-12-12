package ru.nsu.fit.oop;

import java.io.InputStream;
import java.util.*;
import java.lang.reflect.*;

import static javax.swing.UIManager.put;

/**
 * Implements a simple calculator that parses a string in prefix notation.
 */
class Calculator {
    private Map<String, Operation> operationMap;

    public Calculator() {
        operationMap = new HashMap<String, Operation>();
        operationMap.put("+", new Sum(this));
        operationMap.put("-", new Diff(this));
        operationMap.put("*", new Mult(this));
        operationMap.put("/", new Div(this));
        operationMap.put("log", new Log(this));
        operationMap.put("sqrt", new Sqrt(this));
        operationMap.put("sin", new Sin(this));
        operationMap.put("pow", new Pow(this));
        operationMap.put("sin", new Sin(this));
        operationMap.put("cos", new Cos(this));
    }

    /**
     * Selects an operation depending on the provided token.
     * @param operation the token that determines the operation.
     * @return returns an instance of the selected operation.
     */
    Operation callOperation(String operation) {
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
        Operation op = callOperation(operation);
        return op.calculate(parser);
    }
}