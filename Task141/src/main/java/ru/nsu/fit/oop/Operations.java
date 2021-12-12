package ru.nsu.fit.oop;

import java.util.Scanner;

/**
 * The interface that all operations implement.
 */
abstract class Operation {
    Calculator parentCalc;

    /**
     * Contains the actual calculation of an operation. Each operation defines what this method does.
     *
     * @param operands array of operands that are used in operation.
     * @return returns the result of operation as double.
     */
    abstract double result(double[] operands);

    /**
     * Gives the arity of each operation.
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
        double[] operands = new double[2];

        for (int i = 0; i < arity; i++) {
            if (parser.hasNextDouble()) {
                operands[i] = parser.nextDouble();
            } else {
                Operation oper = parentCalc.callOperation(parser.next());
                operands[i] = oper.calculate(parser);
            }
        }

        return result(operands);
    }
}

abstract class BinaryOperation extends Operation {
    int arity = 2;

    double calculate(Scanner parser) {
        double[] operands = new double[2];

        for (int i = 0; i < 2; i++) {
            if (parser.hasNextDouble()) {
                operands[i] = parser.nextDouble();
            } else {
                Operation oper = parentCalc.callOperation(parser.next());
                operands[i] = oper.calculate(parser);
            }
        }

        return result(operands);
    }
}

abstract class UnaryOperation extends Operation {
    int arity = 1;

    double calculate(Scanner parser) {
        double[] operands = new double[2];

        if (parser.hasNextDouble()) {
            operands[0] = parser.nextDouble();
        } else {
            Operation oper = parentCalc.callOperation(parser.next());
            operands[0] = oper.calculate(parser);
        }

        return result(operands);
    }
}

/**
 * Calculates the sum of two numbers.
 */
class Sum extends BinaryOperation {
    public double result(double[] operands) {
        return operands[0] + operands[1];
    }

    public Sum(Calculator parentCalc) {
        this.parentCalc = parentCalc;
    }
}

/**
 * Substracts one number from another.
 */
class Diff extends BinaryOperation {
    public double result(double[] operands) {
        return operands[0] - operands[1];
    }

    public Diff(Calculator parentCalc) {
        this.parentCalc = parentCalc;
    }
}

/**
 * Multiplies one number by another.
 */
class Mult extends BinaryOperation {
    public double result(double[] operands) {
        return operands[0] * operands[1];
    }

    public Mult(Calculator parentCalc) {
        this.parentCalc = parentCalc;
    }
}

/**
 * Divides one number by another.
 */
class Div extends BinaryOperation {
    public double result(double[] operands) {
        return operands[0] / operands[1];
    }

    public Div(Calculator parentCalc) {
        this.parentCalc = parentCalc;
    }
}

/**
 * Calculates the value of one number to the power of another number.
 */
class Pow extends BinaryOperation {
    public double result(double[] operands) {
        return Math.pow(operands[0], operands[1]);
    }

    public Pow(Calculator parentCalc) {
        this.parentCalc = parentCalc;
    }
}

/**
 * Calculates the log of a number.
 */
class Log extends UnaryOperation {
    public double result(double[] operands) {
        return Math.log(operands[0]);
    }

    public Log(Calculator parentCalc) {
        this.parentCalc = parentCalc;
    }
}

/**
 * Calculates the sine of a number.
 */
class Sin extends UnaryOperation {
    public double result(double[] operands) {
        return Math.sin(operands[0]);
    }

    public Sin(Calculator parentCalc) {
        this.parentCalc = parentCalc;
    }
}

/**
 * Calculates the cosine of a number.
 */
class Cos extends UnaryOperation {
    public double result(double[] operands) {
        return Math.cos(operands[0]);
    }

    public Cos(Calculator parentCalc) {
        this.parentCalc = parentCalc;
    }
}

/**
 * Calculates the square root of a number.
 */
class Sqrt extends UnaryOperation {
    public double result(double[] operands) {
        return Math.sqrt(operands[0]);
    }

    public Sqrt(Calculator parentCalc) {
        this.parentCalc = parentCalc;
    }
}
