package ru.nsu.fit.oop;

import java.util.Scanner;

/**
 * The base operation class that all operations inherit.
 */
interface Operation {
    /**
     * Contains the actual calculation of an operation. Each operation defines what this method does.
     *
     * @param operands array of operands that are used in operation.
     * @return returns the result of operation as double.
     */
    double result(double[] operands);

    /**
     * Parses the operands needed for the operation.
     * If another operation is encountered while parsing, recursively calls the other operation first.
     * The result of any operation called from here becomes an operand.
     *
     * @param parser the scanner that is used to get values.
     * @return returns the result of operation as double.
     */
    double calculate(Scanner parser);
}

/**
 * Binary operation base class.
 */
abstract class BinaryOperation implements Operation {
    private int arity = 2;
    private final Calculator parentCalc;

    BinaryOperation(Calculator parentCalc) {
        this.parentCalc = parentCalc;
    }

    public double calculate(Scanner parser) {
        double[] operands = new double[2];

        for (int i = 0; i < 2; i++) {
            if (parser.hasNextDouble()) {
                operands[i] = parser.nextDouble();
            } else {
                Operation oper = parentCalc.getOperation(parser.next());
                operands[i] = oper.calculate(parser);
            }
        }

        return result(operands);
    }
}


/**
 * Unary operation base class.
 */
abstract class UnaryOperation implements Operation {
    private int arity = 1;
    private final Calculator parentCalc;

    UnaryOperation(Calculator parentCalc) {
        this.parentCalc = parentCalc;
    }

    public double calculate(Scanner parser) {
        double[] operands = new double[1];

        if (parser.hasNextDouble()) {
            operands[0] = parser.nextDouble();
        } else {
            Operation oper = parentCalc.getOperation(parser.next());
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
        if (operands.length != 2) throw new IllegalArgumentException("Wrong operand array size");
        return operands[0] + operands[1];
    }

    public Sum(Calculator parentCalc) {
        super(parentCalc);
    }
}

/**
 * Substracts one number from another.
 */
class Diff extends BinaryOperation {
    public double result(double[] operands) {
        if (operands.length != 2) throw new IllegalArgumentException("Wrong operand array size");
        return operands[0] - operands[1];
    }

    public Diff(Calculator parentCalc) {
        super(parentCalc);
    }
}

/**
 * Multiplies one number by another.
 */
class Mult extends BinaryOperation {
    public double result(double[] operands) {
        if (operands.length != 2) throw new IllegalArgumentException("Wrong operand array size");
        return operands[0] * operands[1];
    }

    public Mult(Calculator parentCalc) {
        super(parentCalc);
    }
}

/**
 * Divides one number by another.
 */
class Div extends BinaryOperation {
    public double result(double[] operands) {
        if (operands.length != 2) throw new IllegalArgumentException("Wrong operand array size");
        return operands[0] / operands[1];
    }

    public Div(Calculator parentCalc) {
        super(parentCalc);
    }
}

/**
 * Calculates the value of one number to the power of another number.
 */
class Pow extends BinaryOperation {
    public double result(double[] operands) {
        if (operands.length != 2) throw new IllegalArgumentException("Wrong operand array size");
        return Math.pow(operands[0], operands[1]);
    }

    public Pow(Calculator parentCalc) {
        super(parentCalc);
    }
}

/**
 * Calculates the log of a number.
 */
class Log extends UnaryOperation {
    public double result(double[] operands) {
        if (operands.length != 1) throw new IllegalArgumentException("Wrong operand array size");
        return Math.log(operands[0]);
    }

    public Log(Calculator parentCalc) {
        super(parentCalc);
    }
}

/**
 * Calculates the sine of a number.
 */
class Sin extends UnaryOperation {
    public double result(double[] operands) {
        if (operands.length != 1) throw new IllegalArgumentException("Wrong operand array size");
        return Math.sin(operands[0]);
    }

    public Sin(Calculator parentCalc) {
        super(parentCalc);
    }
}

/**
 * Calculates the cosine of a number.
 */
class Cos extends UnaryOperation {
    public double result(double[] operands) {
        if (operands.length != 1) throw new IllegalArgumentException("Wrong operand array size");
        return Math.cos(operands[0]);
    }

    public Cos(Calculator parentCalc) {
        super(parentCalc);
    }
}

/**
 * Calculates the square root of a number.
 */
class Sqrt extends UnaryOperation {
    public double result(double[] operands) {
        if (operands.length != 1) throw new IllegalArgumentException("Wrong operand array size");
        return Math.sqrt(operands[0]);
    }

    public Sqrt(Calculator parentCalc) {
        super(parentCalc);
    }
}
