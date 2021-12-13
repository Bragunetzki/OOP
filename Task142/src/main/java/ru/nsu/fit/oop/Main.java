package ru.nsu.fit.oop;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Notebook nb = new Notebook(args[0]+".json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();

        while (!nb.parse(input)) {
            input = reader.readLine();
        }
    }
}
