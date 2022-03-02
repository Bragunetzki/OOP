import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

class CompositeFinderTest {

    static ArrayList<ArrayList<Integer>> testLists() throws IOException {
        var list = new ArrayList<ArrayList<Integer>>();

        //list.add(new ArrayList<>(Arrays.asList(6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053)));


        final int TESTSIZE = 7000;
        ArrayList<Integer> primes = new ArrayList<>(TESTSIZE);
        try {
            FileReader rdr = new FileReader("primes.txt");
            BufferedReader buffReader = new BufferedReader(rdr);
            for (int i = 0; i < TESTSIZE; i++) {
                primes.add(Integer.valueOf(buffReader.readLine()));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        list.add(primes);

        return list;
    }

    @ParameterizedTest
    @MethodSource("testLists")
    public void CompositeFinderTestComparison(ArrayList<Integer> list) throws InterruptedException {
        final int MAXTHREADS = 8;
        final int TESTCOUNT = 200;

        long sequentialTime0, sequentialTime1, streamTime0, streamTime1;
        long seqSum = 0, streamSum = 0;
        long[] multithreadTime0 = new long[MAXTHREADS];
        long[] multithreadTime1 = new long[MAXTHREADS];
        long[] multithreadSum = new long[MAXTHREADS];

        for (int test = 1; test <= TESTCOUNT; test++) {
            sequentialTime0 = System.currentTimeMillis();
            assertFalse(CompositeFinder.containsCompositeSequential(list));
            sequentialTime1 = System.currentTimeMillis();
            seqSum += sequentialTime1 - sequentialTime0;

            streamTime0 = System.currentTimeMillis();
            assertFalse(CompositeFinder.containsCompositeStream(list));
            streamTime1 = System.currentTimeMillis();
            streamSum += streamTime1 - streamTime0;

            for (int i = 0; i < MAXTHREADS; i++) {
                multithreadTime0[i] = System.currentTimeMillis();
                assertFalse(CompositeFinder.containsCompositeThreaded(list, i + 1));
                multithreadTime1[i] = System.currentTimeMillis();
                multithreadSum[i] += multithreadTime1[i] - multithreadTime0[i];
            }
        }

        System.out.println("Average sequential time: " + (seqSum / TESTCOUNT) + " ms");
        System.out.println("Average ParallelStream time: " + (streamSum / TESTCOUNT) + " ms");
        for (int i = 0; i < MAXTHREADS; i++) {
            System.out.println("Average time with " + (i + 1) + " threads: " + (multithreadSum[i]/TESTCOUNT) + " ms");
        }
        System.out.println();

    }
}