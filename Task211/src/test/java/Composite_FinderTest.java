import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

class Composite_FinderTest {

    static ArrayList<ArrayList<Integer>> testLists() throws IOException {
        var list = new ArrayList<ArrayList<Integer>>();

        //list.add(new ArrayList<>(Arrays.asList(6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053)));


        final int TESTSIZE = 7000;
        ArrayList<Integer> primes = new ArrayList<>(TESTSIZE);
        try {
            FileReader rdr = new FileReader("primes.txt");
            BufferedReader buff_reader = new BufferedReader(rdr);
            for (int i = 0; i < TESTSIZE; i++) {
                primes.add(Integer.valueOf(buff_reader.readLine()));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        list.add(primes);

        return list;
    }

    @ParameterizedTest
    @MethodSource("testLists")
    public void Composite_FinderTest_Comparison(ArrayList<Integer> list) throws InterruptedException {
        final int MAXTHREADS = 8;
        final int TESTCOUNT = 200;

        long sequential_time0, sequential_time1, stream_time0, stream_time1;
        long seq_sum = 0, stream_sum = 0;
        long[] multithread_time0 = new long[MAXTHREADS];
        long[] multithread_time1 = new long[MAXTHREADS];
        long[] multithread_sum = new long[MAXTHREADS];

        for (int test = 1; test <= TESTCOUNT; test++) {
            sequential_time0 = System.currentTimeMillis();
            assertFalse(Composite_Finder.contains_composite_sequential(list));
            sequential_time1 = System.currentTimeMillis();
            seq_sum += sequential_time1 - sequential_time0;

            stream_time0 = System.currentTimeMillis();
            assertFalse(Composite_Finder.contains_composite_stream(list));
            stream_time1 = System.currentTimeMillis();
            stream_sum += stream_time1 - stream_time0;

            for (int i = 0; i < MAXTHREADS; i++) {
                multithread_time0[i] = System.currentTimeMillis();
                assertFalse(Composite_Finder.contains_composite_threaded(list, i + 1));
                multithread_time1[i] = System.currentTimeMillis();
                multithread_sum[i] += multithread_time1[i] - multithread_time0[i];
            }
        }

        System.out.println("Average sequential time: " + (seq_sum / TESTCOUNT) + " ms");
        System.out.println("Average ParallelStream time: " + (stream_sum / TESTCOUNT) + " ms");
        for (int i = 0; i < MAXTHREADS; i++) {
            System.out.println("Average time with " + (i + 1) + " threads: " + (multithread_sum[i]/TESTCOUNT) + " ms");
        }
        System.out.println();

    }
}