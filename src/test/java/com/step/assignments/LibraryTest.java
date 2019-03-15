package com.step.assignments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LibraryTest {
    private final Book book1 = new Book("Harry Potter", 1123456L);
    private final Book book2 = new Book("Hunger Games", 1123457L);
    private final Book book3 = new Book("How To Kill A Mockingbird", 1234568L);
    private Library library;

    @BeforeEach
    void setup() {
        library = new Library(new HashSet<>(Arrays.asList(book1, book2, book3)));
    }

    @Test
    @DisplayName("should remove the book from main book list")
    void giveBookToReader(){
        BookReader john = new BookReader("John");
        library.addReader(john);
        library.giveBookToReader(john, book1);

        Set<Book> actual = library.getBooks();
        Set<Book> expected = new HashSet<>(Arrays.asList(book2, book3));
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

}