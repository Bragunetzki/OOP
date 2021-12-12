package ru.nsu.fit.oop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Calculator calc = new Calculator();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter an expression in prefix notation (and use spaces!):");
        String input = reader.readLine();

        while (!input.equals("0")) {
            System.out.printf("Result: %f\n", calc.calculate(input));
            System.out.print("Enter an expression in prefix notation (and use spaces!):");
            input = reader.readLine();
        }

        reader.close();
    }

}
