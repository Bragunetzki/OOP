package ru.nsu.fit.oop;

import java.util.Date;

/**
 * Class that describes an entry.
 */
public class Entry {
    private String date;
    private String name;

    /**
     * Getter of date needed for Jason to work.
     * @return - returns the date string.
     */
    public String getDate() {
        return date;
    }

    /**
     * Getter of name needed for Jason to work.
     * @return - returns the name string.
     */
    public String getName() {
        return name;
    }

    /**
     * Default constructor needed for Jason to work.
     */
    public Entry() {
    }

    /**
     * Constructor that initializes a new Entry with set name and date.
     * @param date - the date at which the Entry was made.
     * @param name - the name of the entry.
     */
    public Entry(String date, String name) {
        this.date = date;
        this.name = name;
    }

    /**
     * Converts an Entry to a string.
     * @return - returns a string formatted as *Entry Name*; *Date*
     */
    String toStr() {
        return name + "; " + date;
    }
}
