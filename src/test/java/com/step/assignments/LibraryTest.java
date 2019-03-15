package com.step.assignments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    private final Book book1 = new Book("Harry Potter", 1123456L);
    private final Book book2 = new Book("Hunger Games", 1123457L);
    private final Book book3 = new Book("How To Kill A Mockingbird", 1234568L);

    private final Set<Book> books = new HashSet<>(Arrays.asList(book1, book2, book3));
    private Set<BookReader> readers;

    private Library library;
    private BookReader john;
    private BookReader kane;

    @BeforeEach
    void setup() {
        john = new BookReader("John");
        kane = new BookReader("Kane");
        readers = new HashSet<>(Arrays.asList(john, kane));
        library = new Library(books, readers);
    }

    /* ========= giveBookToReader =========== */

    @Test
    @DisplayName("should remove the book from main book list")
    void giveBookToReader() {
        library.giveBookToReader(john, book1);

        Set<Book> actual = library.getBooks();
        Set<Book> expected = new HashSet<>(Arrays.asList(book2, book3));
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    @DisplayName("should not add the book to the list of removed books")
    void giveBookToReader2() {
        library.giveBookToReader(kane, book2);

        Set<Book> actual = library.getRemovedBooks();
        Set<Book> expected = new HashSet<>(Arrays.asList());
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    @DisplayName("should add the books against the reader in readers register")
    void giveBookToReader3() {
        library.giveBookToReader(kane, book2);

        Set<Book> actual = library.getBooksOfReader(kane);
        Set<Book> expected = new HashSet<>(Arrays.asList(book2));
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    /**
     * Also should use mocks to know reader.borrowBooks is called or not
     */

    /* ============= takeBookFromReader ============== */

    @Test
    @DisplayName("should add book to the list of books")
    void takeBookFromReader() {
        library.giveBookToReader(john, book1);
        library.takeBookFromReader(john, book1);

        Set<Book> actual = library.getBooks();
        Set<Book> expected = new HashSet<>(Arrays.asList(book1, book2, book3));
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    @DisplayName("should remove book against the reader in readers register")
    void takeBookFromReader2(){
        library.giveBookToReader(john, book1);
        library.takeBookFromReader(john, book1);

        Set<Book> actual = library.getBooksOfReader(kane);
        Set<Book> expected = new HashSet<>(Arrays.asList());
        //also can use actual.isEmpty()
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    /**
     * Also use mockers to check if reader.return book was called or not
     */

    /* ========== getBookByName ========== */

    @Test
    @DisplayName("should return the book if its available")
    void getBookByName(){
        String nameOfBook = "Harry Potter";
        Book book = library.getBookByName(nameOfBook);
        assertEquals(nameOfBook, book.getName());
    }

    @Test
    @DisplayName("should return null if its not available")
    void getBookByName2(){
        String nameOfBook = "Bad Book";
        Book book = library.getBookByName(nameOfBook);
        assertNull(book);
    }


}