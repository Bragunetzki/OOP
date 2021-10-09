package ru.nsu.fit.oop.Task112;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Contains functinos for finding all instances of a substring within a text file.
 */
public class SubStringFinder {

    /**
     * Finds all occurences of a string within a file.
     *
     * @param filename    - the name of the file to search in.
     * @param inputString - the string to search for.
     * @return - returns a list of all indexes at which the substring can be found.
     */
    static List<Integer> findStringInFile(String filename, String inputString) {
        try (FileReader reader = new FileReader(filename)) {
            return findSubString(inputString, reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Finds a substring within a text.
     *
     * @param inputString - the string to search for.
     * @param rdr         - the reader that provides the text.
     * @return - returns a list of all indexes at which the substring can be found.
     */
    static List<Integer> findSubString(String inputString, Reader rdr) throws IOException {
        if (inputString == null) throw new IllegalArgumentException("The input string is null!");

        BufferedReader reader = new BufferedReader(rdr);

        int ssLen = inputString.length();
        char[] subString = inputString.toCharArray();

        List<Integer> results = new ArrayList<>();

        int bufferSize = 1000;
        char[] buffer = new char[bufferSize + ssLen + 1];

        int[] zArr = new int[buffer.length];
        buildZarr(subString, zArr, 0);
        zArr[ssLen] = 0;

        System.arraycopy(subString, 0, buffer, 0, ssLen); //copy subString to beginning of buffer
        buffer[ssLen] = '0';
        reader.mark(bufferSize);

        while (reader.read(buffer, ssLen + 1, bufferSize) != -1) {
            buildZarr(buffer, zArr, ssLen + 1);
            for (int i = 1; i < zArr.length; i++) {
                if (zArr[i] == ssLen) {
                    results.add(i - ssLen - 1);
                }
            }

            reader.reset();
            for (int i = 0; i < bufferSize + 1; i++) reader.read();
            reader.mark(bufferSize);
        }

        return results;
    }

    /**
     * Builds an Z-function array for a string.
     *
     * @param str      - The string that the array is built off of.
     * @param zArr     - The array in which to store data.
     * @param startPos - The position with which to start modifying the array (useful when str starts with the pattern string).
     */
    static private void buildZarr(char[] str, int[] zArr, int startPos) {
        int len = str.length;

        int l = 0, r = 0;

        for (int i = startPos; i < len; i++) {
            if (i > r) {
                l = r = i;

                while (r < len && str[r - l] == str[r])
                    r++;

                zArr[i] = r - l;
                r--;
            } else {
                int k = i - l;

                if (zArr[k] < r - i + 1)
                    zArr[i] = zArr[k];
                else {
                    l = i;
                    while (r < len && str[r - l] == str[r])
                        r++;

                    zArr[i] = r - l;
                    r--;
                }
            }
        }
    }
}
