package ru.nsu.fit.oop;

/**
 * Binary operation base class.
 */
public abstract class BinaryOperation extends Operation {
    public BinaryOperation(Calculator parentCalc) {
        super(parentCalc, 2);
    }
}
