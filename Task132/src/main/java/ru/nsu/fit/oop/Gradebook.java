package ru.nsu.fit.oop;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains a student's grades.
 */
public class Gradebook {
    private Map<String, Integer> grades;

    public Gradebook() {
        this.grades = new HashMap<String, Integer>();
    }

    public void addGrade(String subject, Integer grade) {
        grades.put(subject, grade);
    }

    public float averageGrade() {
        float avg = 0;
        for (Map.Entry<String, Integer> entry : grades.entrySet()) {
            avg += entry.getValue();
        }

        avg /= grades.size();

        return avg;
    }

    public boolean increasedStipend() {
        for (Map.Entry<String, Integer> entry : grades.entrySet()) {
            if(entry.getValue() != 5) {
                return false;
            }
        }

        return true;
    }

    public boolean redDiploma() {
        int count5 = 0;

        for (Map.Entry<String, Integer> entry : grades.entrySet()) {
            if(entry.getValue() == 3) {
                return false;
            }


        }
    }
}
