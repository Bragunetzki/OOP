package ru.nsu.fit.oop.Task112;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Contains functinos for finding all instances of a substring within a text file.
 */
public class SubStringFinder {

    /**
     * Finds all instances of inputString in file.
     * @param filename - Name of text file within which to search.
     * @param inputString - The substring that needs to be found.
     * @return - Returns a list of indexes at which the substring is found.
     */
    static List<Integer> findSubString(String filename, String inputString) {
        int ssLen = inputString.length();
        char[] subString = inputString.toCharArray();

        List<Integer> results = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            int bufferSize = 1000;
            char[] buffer = new char[bufferSize+ssLen+1];

            int[] zArr = new int[buffer.length];
            buildZarr(subString, zArr, 0);
            zArr[ssLen]=0;

            System.arraycopy(subString, 0, buffer, 0, ssLen); //copy subString to beginning of buffer
            buffer[ssLen]='0';
            reader.mark(bufferSize);

            while (reader.read(buffer, ssLen+1, bufferSize) != -1) {
                buildZarr(buffer, zArr, ssLen+1);
                for (int i = 1; i < zArr.length; i++) {
                    if (zArr[i] == ssLen) {
                        results.add(i-ssLen-1);
                    }
                }

                reader.reset();
                for (int i = 0; i < bufferSize+1; i++) reader.read();
                reader.mark(bufferSize);
            }
        }
            catch (IOException e) {
                e.printStackTrace();
            }

        return results;
    }

    /**
     * Builds an Z-function array for a string.
     * @param str - The string that the array is built off of.
     * @param zArr - The array in which to store data.
     * @param startPos - The position with which to start modifying the array (useful when str starts with the pattern string).
     */
    static private void buildZarr(char[] str, int[] zArr, int startPos) {
        int len = str.length;

        int l = 0, r = 0;

        for (int i = startPos; i < len; i++) {
            if (i>r) {
                l = r = i;

                while (r < len && str[r-l]==str[r])
                    r++;

                zArr[i]=r-l;
                r--;
            }
            else {
                int k = i-l;

                if (zArr[k]<r-i+1)
                    zArr[i] = zArr[k];
                else {
                    l=i;
                    while(r < len && str[r-l]==str[r])
                        r++;

                    zArr[i]=r-l;
                    r--;
                }
            }
        }
    }
}
