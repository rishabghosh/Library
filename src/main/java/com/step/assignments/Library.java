package com.step.assignments;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Library {
    private final Set<Book> books = new HashSet<>();
    private final Set<BookReader> bookReaders = new HashSet<>();
    private final Set<Book> removedBooks = new HashSet<>();

    //BookReader and Books are stored as a Key value pair
    private final Map<BookReader, Set<Book>> readersRegister = new HashMap<>();
    private final Map<Book, BookReader> bookRegister = new HashMap<>();

    public Library(Set<Book> books) {
        this.books.addAll(books);
    }

    //library should have methods like
    //getCurrentReaderOf book should access bookRegister
    //getAllBooksOfReader should access readersRegister


    /* ============= Internal Methods ============== */

    private boolean isSameBook(Book book, String nameOfBook) {
        return book.getName().equals(nameOfBook);
    }

    private boolean hasBook(Book book) {
        return this.books.contains(book);
    }

    private void addNewBook(Book newBook) {
        this.books.add(newBook);
    }

    private void removeBook(Book book) {
        if (hasBook(book)) {
            this.books.remove(book);
            this.removedBooks.add(book);
            return;
        }
        System.out.printf("Book %s does not exist", book.getName());
    }

    private boolean searchByName(String nameOfBook) {
        for (Book book : this.books) {
            if (isSameBook(book, nameOfBook)) {
                return true;
            }
        }
        return false;
    }

    private void addInReadersRegister(BookReader reader, Book book){
        Set<Book> booksOfReader = this.readersRegister.get(reader);
        System.out.println(booksOfReader);
        booksOfReader.add(book);
    }

    private void removePermanently(Book book) {
        this.removedBooks.remove(book);
    }

    private void removeFromReadersRegister(BookReader reader, Book book) {
        Set<Book> booksOfReader = this.readersRegister.get(reader);
        booksOfReader.remove(book);
    }

    /* ========= Methods For Reader ========== */


    public void giveBookToReader(BookReader reader, Book book){
        this.books.remove(book);
        addInReadersRegister(reader, book);
        reader.borrowBook(book);
    }

    public void takeBookFromReader(BookReader reader, Book book) {
        reader.returnBook(book);
        removeFromReadersRegister(reader, book);
        this.addNewBook(book);
    }


    /* ======= Methods For Testing ======= */


    public Set<Book> getBooks() {
        return new HashSet<>(this.books);
    }

    public void addReader(BookReader reader) {
        this.bookReaders.add(reader);
        this.readersRegister.put(reader, new HashSet<>());
    }

    public Set<BookReader> getBookReaders() {
        return new HashSet<>(this.bookReaders);
    }

    public Set<Book> getRemovedBooks() {
        return new HashSet<>(this.removedBooks);
    }


    /* =========== Librarian =========== */


    private class Librarian{
        public boolean isBookRemoved(Book book){
            return getRemovedBooks().contains(book);
        }

        public BookReader getCurrentReaderOf(Book book){
            for (BookReader reader: getBookReaders()) {
                if(reader.hasBorrowed(book)){
                    return reader;
                }
            }
            return null;
        }

        public Set<Book> getAllBooksOfReader(BookReader reader){
            return reader.getBorrowedBooks();
        }

        public void bringBack(Book book){
            if(isBookRemoved(book)){
                removePermanently(book);
                addNewBook(book);
                return;
            }
            System.out.println("Book does not exist in removed list");
        }

        public void addBookToLibrary(Book book) {
            addNewBook(book);
        }

        public void removeBookFromLibrary(Book book) {
            removeBook(book);
        }
    }
}
