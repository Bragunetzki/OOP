package ru.nsu.fit.oop.Task112;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubStringFinder {

    static List<Integer> findSubString(String filename, String inputString) {
        int ssLen = inputString.length();
        char[] subString = inputString.toCharArray();
        List<Integer> results = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            char[] buffer = new char[ssLen];
            reader.mark(ssLen);

            for (int index = 0; reader.read(buffer, 0, ssLen) != -1; index++) {

                if (Arrays.equals(buffer, subString)) {
                    results.add(index);
                }

                reader.reset();
                reader.read();
                reader.mark(ssLen);
            }
        }
            catch (IOException e) {
                e.printStackTrace();
            }

        return results;
    }
}
