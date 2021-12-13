package ru.nsu.fit.oop;

/**
 * Class that describes an entry.
 */
public class Entry {
    private String date;
    private String name;
    private String content;

    /**
     * Getter of date needed for Jason to work.
     *
     * @return - returns the date string.
     */
    public String getDate() {
        return date;
    }

    /**
     * Getter of name needed for Jason to work.
     *
     * @return - returns the name string.
     */
    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    /**
     * Default constructor needed for Jason to work.
     */
    public Entry() {
    }

    /**
     * Constructor that initializes a new Entry with set name, date and content.
     *
     * @param date    - the date at which the Entry was made.
     * @param name    - the name of the entry.
     * @param content - the content of the Entry.
     */
    public Entry(String date, String name, String content) {
        this.date = date;
        this.name = name;
        this.content = content;
    }

    /**
     * Converts an Entry to a string.
     *
     * @return - returns a string formatted as *Entry Name*; *Date*; *Content*.
     */
    String toStr() {
        return name + "; " + date + "; " + content;
    }
}
