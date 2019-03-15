package com.step.assignments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookReaderTest {
    private final Book book1 = new Book("Harry Potter", 1123456L);
    private final Book book2 = new Book("Hunger Games", 1123457L);
    private final Book book3 = new Book("How To Kill A Mockingbird", 1234568L);
    private final Book book4 = new Book("Sherlock Homes", 1234569L);
    private final Book book5 = new Book("Kite Runner", 1234570L);

    private BookReader reader;
    private Library library;


    @BeforeEach
    void setup() {
        library = new Library();
        library.addNewBook(book1);
        library.addNewBook(book2);
        library.addNewBook(book3);

        reader = new BookReader("John");
        reader.borrowBook(book4);
        reader.borrowBook(book5);
    }


    @Test
    @DisplayName("should add the book to the borrowedBooks list")
    void borrowBooks(){
        reader.borrowBook(book1);
        Set<Book> actual = reader.getBorrowedBooks();
        Set<Book> expected = new HashSet<>(Arrays.asList(book4, book5, book1));
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    @DisplayName("should return true if already has the book of this name")
    void hasBorrowed() {
        assertTrue(reader.hasBorrowed(book4));
    }

    @Test
    @DisplayName("should return false if does not have any book of this name")
    void hasBorrowed2() {
        assertFalse(reader.hasBorrowed(book1));
    }

    @Test
    @DisplayName("should remove the book from borrowedBooks list if the book exists")
    void returnBook(){
        reader.returnBook(book4);
        Set<Book> actual = reader.getBorrowedBooks();
        Set<Book> expected = new HashSet<>(Arrays.asList(book5));
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    @DisplayName("give a list and a name of book, should return true if the book of similar name has been found")
    void searchBook1(){
        List<Book> books = Arrays.asList(book1, book2, book3, book4, book5);
        assertTrue(reader.searchBook(books, "Harry Potter"));
    }
}