package ru.nsu.fit.oop;

import java.util.Scanner;

import static ru.nsu.fit.oop.Calculator.callOperation;

/**
 * The interface that all operations implement.
 */
abstract class Operation {
    /**
     * Contains the actual calculation of an operation. Each operation defines what this method does.
     *
     * @param operands array of operands that are used in operation.
     * @return returns the result of operation as double.
     */
    abstract double result(double[] operands);

    /**
     * Gives the arity of each operation
     *
     * @return returns the arity of the operation.
     */
    int arity;

    /**
     * Parses the operands needed for the operation.
     * If another operation is encountered while parsing, recursively calls the other operation first.
     * The result of any operation called from here becomes an operand.
     *
     * @param parser the scanner that is used to get values.
     * @return returns the result of operation as double.
     */
    double calculate(Scanner parser) {
        double[] operands = new double[arity];

        for (int i = 0; i < arity; i++) {
            if (parser.hasNextDouble()) {
                operands[i] = parser.nextDouble();
            } else {
                Operation oper = callOperation(parser.next());
                operands[i] = oper.calculate(parser);
            }
        }

        return result(operands);
    }
}

/**
 * Calculates the sum of two numbers.
 */
class Sum extends Operation {
    public double result(double[] operands) {
        return operands[0] + operands[1];
    }

    public Sum() {
        arity = 2;
    }
}

/**
 * Substracts one number from another.
 */
class Diff extends Operation {
    public double result(double[] operands) {
        return operands[0] - operands[1];
    }

    public Diff() {
        arity = 2;
    }
}

/**
 * Multiplies one number by another.
 */
class Mult extends Operation {
    public double result(double[] operands) {
        return operands[0] * operands[1];
    }

    public Mult() {
        arity = 2;
    }
}

/**
 * Divides one number by another.
 */
class Div extends Operation {
    public double result(double[] operands) {
        return operands[0] / operands[1];
    }

    public Div() {
        arity = 2;
    }
}

/**
 * Calculates the value of one number to the power of another number.
 */
class Pow extends Operation {
    public double result(double[] operands) {
        return Math.pow(operands[0], operands[1]);
    }

    public Pow() {
        arity = 2;
    }
}

/**
 * Calculates the log of a number.
 */
class Log extends Operation {
    public double result(double[] operands) {
        return Math.log(operands[0]);
    }

    public Log() {
        arity = 1;
    }
}

/**
 * Calculates the sine of a number.
 */
class Sin extends Operation {
    public double result(double[] operands) {
        return Math.sin(operands[0]);
    }

    public Sin() {
        arity = 1;
    }
}

/**
 * Calculates the cosine of a number.
 */
class Cos extends Operation {
    public double result(double[] operands) {
        return Math.cos(operands[0]);
    }

    public Cos() {
        arity = 1;
    }
}

/**
 * Calculates the square root of a number.
 */
class Sqrt extends Operation {
    public double result(double[] operands) {
        return Math.sqrt(operands[0]);
    }

    public Sqrt() {
        arity = 1;
    }
}
