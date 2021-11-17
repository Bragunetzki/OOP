package ru.nsu.fit.oop;

import java.io.InputStream;
import java.util.*;
import java.lang.reflect.*;

/**
 * Implements a simple calculator that parses a string in prefix notation.
 */
abstract class Calculator {
    /**
     * The interface that all operations implement.
     */
    interface Operation {
        /**
         * Contains the actual calculation of an operation. Each operation defines what this method does.
         * @param operands array of operands that are used in operation.
         * @return returns the result of operation as double.
         */
        double result(double[] operands);

        /**
         * Gives the arity of each operation
         * @return returns the arity of the operation.
         */
        int arity();

        /**
         * Parses the operands needed for the operation.
         * If another operation is encountered while parsing, recursively calls the other operation first.
         * The result of any operation called from here becomes an operand.
         * @param parser the scanner that is used to get values.
         * @return returns the result of operation as double.
         */
        default double calculate(Scanner parser) {
            double[] operands = new double[arity()];

            for (int i = 0; i < arity(); i++) {
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
    static class Sum implements Operation {
        public double result(double[] operands) {
            return operands[0] + operands[1];
        }

        public int arity() {
            return 2;
        }
    }

    /**
     * Substracts one number from another.
     */
    static class Diff implements Operation {
        public double result(double[] operands) {
            return operands[0] - operands[1];
        }

        public int arity() {
            return 2;
        }
    }

    /**
     * Multiplies one number by another.
     */
    static class Mult implements Operation {
        public double result(double[] operands) {
            return operands[0] * operands[1];
        }

        public int arity() {
            return 2;
        }
    }

    /**
     * Divides one number by another.
     */
    static class Div implements Operation {
        public double result(double[] operands) {
            return operands[0] / operands[1];
        }

        public int arity() {
            return 2;
        }
    }

    /**
     * Calculates the value of one number to the power of another number.
     */
    static class Pow implements Operation {
        public double result(double[] operands) {
            return Math.pow(operands[0], operands[1]);
        }

        public int arity() {
            return 2;
        }
    }

    /**
     * Calculates the log of a number.
     */
    static class Log implements Operation {
        public double result(double[] operands) {
            return Math.log(operands[0]);
        }

        public int arity() {
            return 1;
        }
    }

    /**
     * Calculates the sine of a number.
     */
    static class Sin implements Operation {
        public double result(double[] operands) {
            return Math.sin(operands[0]);
        }

        public int arity() {
            return 1;
        }
    }

    /**
     * Calculates the cosine of a number.
     */
    static class Cos implements Operation {
        public double result(double[] operands) {
            return Math.cos(operands[0]);
        }

        public int arity() {
            return 1;
        }
    }

    /**
     * Calculates the square root of a number.
     */
    static class Sqrt implements Operation {
        public double result(double[] operands) {
            return Math.sqrt(operands[0]);
        }

        public int arity() {
            return 1;
        }
    }

    /**
     * Selects an operation depending on the provided token.
     * @param operation the token that determines the operation.
     * @return returns an instance of the selected operation.
     */
    static Operation callOperation(String operation) {
       switch (operation) {
            case ("+") -> {
                return new Sum();
            }
            case ("-") -> {
                return new Diff();
            }
            case ("*") -> {
                return new Mult();
            }
            case ("/") -> {
                return new Div();
            }
            case ("log") -> {
                return new Log();
            }
            case ("pow") -> {
                return new Pow();
            }
            case ("sqrt") -> {
                return new Sqrt();
            }
            case ("sin") -> {
                return new Sin();
            }
            case ("cos") -> {
                return new Cos();
            }
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        }
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
        Operation oper = callOperation(operation);
        return oper.calculate(parser);
    }
}