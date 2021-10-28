package ru.nsu.fit.oop;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains a student's grades.
 */
public class Gradebook {
    private HashMap<String, Integer>[] grades;
    private int gradecount;

    /**
     * Initializes the gradebook.
     */
    public Gradebook() {
        grades = (HashMap<String, Integer>[]) new HashMap[8];
        for (int i = 0; i < 8; i++) grades[i] = new HashMap<String, Integer>();
        gradecount = 0;
    }

    /**
     * Adds a new subject and its grade.
     *
     * @param subject - title of subject to be added.
     * @param sem     - number of semester to add grade to.
     * @param grade   - the grade itself.
     */
    public void addGrade(String subject, int sem, Integer grade) {
        grades[sem - 1].put(subject, grade);
        gradecount++;
    }

    /**
     * @return - returns the average grade.
     */
    public float averageGrade() {
        float avg = 0;

        for (int i = 0; i < 8; i++) {
            for (Map.Entry<String, Integer> entry : grades[i].entrySet()) {
                avg += entry.getValue();
            }
        }

        avg /= gradecount;

        return avg;
    }

    /**
     * @param sem - current semester.
     * @return - returns true if an increased stipend is possible.
     */
    public boolean increasedStipend(int sem) {
        if (sem > 8 || sem < 1) throw new IllegalArgumentException("Semester value has to be between 1 and 8!");

        for (Map.Entry<String, Integer> entry : grades[sem - 1].entrySet()) {
            if (entry.getValue() != 5) {
                return false;
            }
        }

        return true;
    }

    /**
     * @return - returns true if a red diploma is possible.
     */
    public boolean redDiploma() {
        Map<String, Integer> diploma = new HashMap<String, Integer>();

        for (int i = 0; i < 8; i++) {
            for (Map.Entry<String, Integer> entry : grades[i].entrySet()) {
                if (entry.getValue() == 3) {
                    return false;
                }

                if (entry.getValue() == 5) {
                    if (!diploma.containsKey(entry.getKey())) {
                        diploma.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }

        float ratioOf5 = (float) diploma.size() / gradecount;

        if (ratioOf5 >= 0.75) return true;
        else return false;
    }
}
