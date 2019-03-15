package com.step.assignments;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LibrarianTest {

    private final Book book1 = new Book("Harry Potter", 1123456L);
    private final Book book2 = new Book("Hunger Games", 1123457L);
    private final Book book3 = new Book("How To Kill A Mockingbird", 1234568L);
    private final Book book4 = new Book("Sherlock Homes", 1234569L);

    private Library library;
    private Librarian librarian;
    private BookReader reader1;

    @BeforeEach
    void setUp() {
        reader1 = new BookReader("John");

        library = new Library();
        library.addNewBook(book1);
        library.addNewBook(book2);
        library.addNewBook(book3);

        library.addReader(reader1);

        librarian = new Librarian(library);
    }


    @AfterEach
    void tearDown() {
    }


    @Test
    @DisplayName("should return the reader when the reader has the particular book")
    void currentReaderOfBook1() {
        reader1.borrowBook(book1);

        BookReader actual = librarian.currentReaderOfBook(book1);
        BookReader expected = reader1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return null if there is no reader of the book")
    void currentReaderOfBook2() {
        BookReader actual = librarian.currentReaderOfBook(book1);
        assertEquals(null, actual);
    }

    @Test
    @DisplayName("should return a list of all books the reader has")
    void checkAllBooksOfReader() {
        reader1.borrowBook(book1);
        reader1.borrowBook(book2);
        reader1.borrowBook(book3);

        Set<Book> actual = librarian.checkAllBooksOfReader(reader1);
        Set<Book> expected = new HashSet<>(Arrays.asList(book1, book2, book3));
        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("should remove the book from removed books list")
    void bringBack1() {
        library.removeBook(book1);
        library.removeBook(book2);

        librarian.bringBack(book1);

        Set<Book> actual = library.getRemovedBooks();
        List<Book> expected = Arrays.asList(book2);
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    @DisplayName("should add the book to library")
    void bringBack2() {
        library.removeBook(book1);

        librarian.bringBack(book1);

        Set<Book> actual = library.getBooks();
        Set<Book> expected = new HashSet<>(Arrays.asList(book1, book2, book3));
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    @DisplayName("should be able to add new book to library")
    void addBookToLibrary(){
        librarian.addBookToLibrary(book4);
        Set<Book> actual = library.getBooks();
        Set<Book> expected = new HashSet<>(Arrays.asList(book1, book2, book3, book4));
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    @DisplayName("should be able to delete a book from library")
    void removeBookFromLibrary(){
        librarian.removeBookFromLibrary(book3);
        Set<Book> actual = library.getBooks();
        Set<Book> expected = new HashSet<>(Arrays.asList(book1, book2));
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    @DisplayName("should return true if the book has been remove in the past")
    void isBookRemoved(){
        librarian.removeBookFromLibrary(book3);
        assertTrue(librarian.isBookRemoved(book3));
    }

    @Test
    @DisplayName("should return false if the book has been remove in the past")
    void isBookRemoved2(){
        assertFalse(librarian.isBookRemoved(book3));
    }

}