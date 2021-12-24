package ru.nsu.fit.oop;

/**
 * Unary operation base class.
 */
public abstract class UnaryOperation extends Operation {
    public UnaryOperation(Calculator parentCalc) {
        super(parentCalc, 1);
    }
}
