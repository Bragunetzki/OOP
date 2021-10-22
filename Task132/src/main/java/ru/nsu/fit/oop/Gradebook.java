package ru.nsu.fit.oop;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains a student's grades.
 */
public class Gradebook {
    private Map<String, Integer> grades;

    /**
     * Initializes the gradebook.
     */
    public Gradebook() {
        this.grades = new HashMap<String, Integer>();
    }

    /**
     * Adds a new subject and its grade.
     *
     * @param subject - title of subject to be added.
     * @param grade - the grade itself.
     */
    public void addGrade(String subject, Integer grade) {
        grades.put(subject, grade);
    }

    /**
     * @return - returns the average grade.
     */
    public float averageGrade() {
        float avg = 0;
        for (Map.Entry<String, Integer> entry : grades.entrySet()) {
            avg += entry.getValue();
        }

        avg /= grades.size();

        return avg;
    }

    /**
     * @return - returns true if an increased stipend is possible.
     */
    public boolean increasedStipend() {
        for (Map.Entry<String, Integer> entry : grades.entrySet()) {
            if(entry.getValue() != 5) {
                return false;
            }
        }

        return true;
    }

    /**
     * @return - returns true if a red diploma is possible.
     */
    public boolean redDiploma() {
        int count5 = 0;

        for (Map.Entry<String, Integer> entry : grades.entrySet()) {
            if(entry.getValue() == 3) {
                return false;
            }

            if(entry.getValue() == 5) {
                count5++;
            }
        }

       float ratioOf5 = (float) count5/grades.size();

        if (ratioOf5 >= 0.75) return true;
        else return false;
    }
}
