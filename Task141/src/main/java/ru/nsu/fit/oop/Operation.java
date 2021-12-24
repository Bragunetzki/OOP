package ru.nsu.fit.oop;

import java.util.Scanner;

/**
 * The base operation class that all operations inherit.
 */
public abstract class Operation {
    private final int arity;
    private final Calculator parentCalc;

    /**
     * Contains the actual calculation of an operation. Each operation defines what this method does.
     *
     * @param operands array of operands that are used in operation.
     * @return returns the result of operation as double.
     */
    abstract protected double result(double[] operands);

    /**
     * Operation constructor.
     *
     * @param parentCalc - the parent Calculator.
     * @param arity - the arity of the operation.
     */
    public Operation(Calculator parentCalc, int arity) {
        this.arity = arity;
        this.parentCalc = parentCalc;
    }

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
                Operation oper = parentCalc.getOperation(parser.next());
                operands[i] = oper.calculate(parser);
            }
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
class Sub extends BinaryOperation {
    public double result(double[] operands) {
        if (operands.length != 2) throw new IllegalArgumentException("Wrong operand array size");
        return operands[0] - operands[1];
    }

    public Sub(Calculator parentCalc) {
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
