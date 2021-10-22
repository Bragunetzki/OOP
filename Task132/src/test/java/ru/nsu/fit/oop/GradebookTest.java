package ru.nsu.fit.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradebookTest {
    public Gradebook testBook() {
        Gradebook book = new Gradebook();
        book.addGrade("Introduction to algebra and math analysis - sem1", 3);
        book.addGrade("Introduction to discrete maths and mathematical logic - sem1", 4);
        book.addGrade("History", 4);
        book.addGrade("Declarative programming - sem1", 4);
        book.addGrade("Basics of speech culture", 4);
        book.addGrade("Imperative programming - sem1", 5);
        book.addGrade("Introduction to algebra and math analysis - sem2", 4);
        book.addGrade("Imperative programming - sem2", 5);
        book.addGrade("Digital platforms", 5);
        book.addGrade("Introduction to discrete maths and mathematical logic - sem2", 5);
        book.addGrade("English - sem2", 4);
        book.addGrade("Declarative programming - sem2", 4);

        return book;
    }

    @Test
    public void testGradebookAvg() {
        Gradebook book = testBook();

        assertEquals(4.25, book.averageGrade());
    }

    @Test
    public void testGradebookIncreasedStipend() {
        Gradebook badBook = testBook();
        Gradebook goodBook = new Gradebook();
        goodBook.addGrade("SomeSubject", 5);

        assertTrue(goodBook.increasedStipend());
        assertFalse(badBook.increasedStipend());
    }



}