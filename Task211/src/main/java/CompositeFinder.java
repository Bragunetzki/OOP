import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Math.sqrt;

/**
 * Finds composite numbers in lists using three different solutions.
 */
public abstract class CompositeFinder {
    /**
     * Determines if the number n is composite or not.
     * @param n - the number that has to be tested.
     * @return - returns true if number is composite.
     */
    public static boolean isComposite(Integer n) {
        if (n <= 1) return true;

        for (int i = 2; i < sqrt(n); i++) {
            if (n % i == 0) return true;
        }

        return false;
    }

    /**
     * Determines if list has a composite number.
     * Simply iterates through a list.
     * @param list - the list withing which to search.
     * @return - returns true if list has at least one composite number.
     */
    public static boolean containsCompositeSequential(List<Integer> list) {
        for (Integer i : list) {
            if (isComposite(i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if list has a composite number.
     * Searches list using ParallelStream.
     * @param list - the list withing which to search.
     * @return - returns true if list has at least one composite number.
     */
    public static boolean containsCompositeStream(List<Integer> list) {
        List<Integer> filteredList = list.parallelStream().filter(CompositeFinder::isComposite).toList();
        return !filteredList.isEmpty();
    }

    /**
     * Determines if list has a composite number.
     * Splits the list iteration into different threads of execution.
     * @param list - the list withing which to search.
     * @param tNum - the number of threads to split the array into.
     * @return - returns true if list has at least one composite number.
     */
    public static boolean containsCompositeThreaded(List<Integer> list, int tNum) throws InterruptedException {
        if (tNum > list.size()) throw new InvalidParameterException("Number of threads cannot exceed number of array elements!");
        int partitionSize = list.size() / tNum;

        AtomicBoolean res = new AtomicBoolean(false);

        List<Thread> threads = new ArrayList<>(tNum);
        for (int i = 0; i < tNum; i++) {
            int start = i*partitionSize;
            int end;
            if (i == tNum -1) end = list.size();
                else end = Math.min(start + partitionSize, list.size());

            threads.add(new Thread(() -> {
                List<Integer> threadList = list.subList(start, end);
                for (Integer n : threadList) {
                    if (isComposite(n)) {
                        res.set(true);
                        break;
                    }
                    if (res.get()) {
                        break;
                    }
                }

            }));
            threads.get(i).start();
        }

        for (Thread t : threads) {
            t.join();
        }
        return res.get();
    }
}
