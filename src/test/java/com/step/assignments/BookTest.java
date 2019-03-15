package com.step.assignments;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {

    @Test
    @DisplayName("Should return the name of the book")
    void getName() {
        Book book = new Book("Harry Potter", 123456L);
        String actual = book.getName();
        String expected  = "Harry Potter";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return the id of the book")
    void getId() {
        Book book = new Book("Harry Potter", 123456L);
        Long actual = book.getId();
        Long expected = 123456L;
        assertEquals(expected, actual);

    }
}