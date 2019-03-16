package com.step.assignments;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Library {
    private final Set<Book> books = new HashSet<>();
    private final Set<BookReader> bookReaders = new HashSet<>();
    private final Set<Book> removedBooks = new HashSet<>();

    private final Map<BookReader, Set<Book>> readersRegister = new HashMap<>();
    private final Map<Book, BookReader> bookRegister = new HashMap<>();

    public Library(Set<Book> books, Set<BookReader> bookReaders) {
        this.books.addAll(books);
        this.bookReaders.addAll(bookReaders);
        registerAllReaders(bookReaders);
        registerAllBooks(books);
    }

    public Library() {
    }

    @Override
    public String toString() {
        return String.format("Library -- \n %s ", this.formatReadersRegister());
    }

    private String formatReadersRegister() {
        String details = "";

        details += "Readers ------ ";
        for (BookReader reader : this.bookReaders) {
            details += reader.getName();
            details += " ,";
        }



        details += "\nBooks ------";
        for (Book book: this.bookRegister.keySet()) {
            details += book.getName();
            details += " ,";
        }

        return details;
    }

    /* ============= Internal Methods ============== */


    private void registerAllBooks(Set<Book> books) {
        for (Book book : books) {
            this.entryNewBook(book);
        }
    }

    private void entryNewBook(Book book) {
        this.bookRegister.put(book, null);
    }

    private void registerAllReaders(Set<BookReader> bookReaders) {
        for (BookReader bookReader : bookReaders) {
            this.registerNewReader(bookReader);
        }
    }

    private boolean isSameBook(Book book, String nameOfBook) {
        return book.getName().equals(nameOfBook);
    }

    private boolean hasBook(Book book) {
        return this.books.contains(book);
    }

    private void addNewBook(Book newBook) {
        this.books.add(newBook);
        this.bookRegister.put(newBook, null);
    }

    private void removeBook(Book book) {
        if (hasBook(book)) {
            this.books.remove(book);
            this.removedBooks.add(book);
            return;
        }
        System.out.printf("Book %s does not exist", book.getName());
    }

    private void addBookInReadersRegister(BookReader reader, Book book) {
        Set<Book> booksOfReader = this.readersRegister.get(reader);
        booksOfReader.add(book);
    }

    private void addReaderInBookRegister(Book book, BookReader reader) {
        this.bookRegister.put(book, reader);
    }

    private void removePermanently(Book book) {
        this.removedBooks.remove(book);
    }

    private void removeBookFromReadersRegister(BookReader reader, Book book) {
        Set<Book> booksOfReader = this.readersRegister.get(reader);
        booksOfReader.remove(book);
    }

    private void registerNewReader(BookReader reader) {
        this.readersRegister.put(reader, new HashSet<>());
    }

    private Map<Book, BookReader> getBookRegister() {
        return this.bookRegister;
    }

    private boolean isReaderRegistered(BookReader reader) {
        return this.bookReaders.contains(reader);
    }


    /* ========= Methods For Reader ========== */


    public void giveBookToReader(BookReader reader, Book book) {
        this.books.remove(book);
        addBookInReadersRegister(reader, book);
        addReaderInBookRegister(book, reader);
        reader.borrowBook(book);
    }

    public void takeBookFromReader(BookReader reader, Book book) {
        reader.returnBook(book);
        removeBookFromReadersRegister(reader, book);
        this.addNewBook(book);
    }

    public Book getBookByName(String nameOfBook) {
        for (Book book : this.books) {
            if (isSameBook(book, nameOfBook)) {
                return book;
            }
        }
        return null;
    }


    /* ======= Methods For Testing ======= */


    public Set<Book> getBooks() {
        return new HashSet<>(this.books);
    }

    public void addReader(BookReader reader) {
        this.bookReaders.add(reader);
        this.registerNewReader(reader);
    }

    public Set<BookReader> getBookReaders() {
        return new HashSet<>(this.bookReaders);
    }

    public Set<Book> getRemovedBooks() {
        return new HashSet<>(this.removedBooks);
    }

    public Map<BookReader, Set<Book>> getReadersRegister() {
        return new HashMap<>(this.readersRegister);
    }

    public Set<Book> getBooksOfReader(BookReader reader) {
        Set<Book> books = this.readersRegister.get(reader);
        return new HashSet<>(books);
    }

    public Librarian getLibrarian() {
        return new Librarian();
    }


    /* =========== Librarian =========== */


    public class Librarian {
        public boolean wasBookRemoved(Book book) {
            return getRemovedBooks().contains(book);
        }

        public BookReader getCurrentReaderOf(Book book) {
            Map<Book, BookReader> bookRegister = getBookRegister();
            return bookRegister.get(book);
        }

        public Set<Book> getAllBooksOfReader(BookReader reader) {
            if (isReaderRegistered(reader)) {
                Map<BookReader, Set<Book>> readersRegister = getReadersRegister();
                return readersRegister.get(reader);
            }
            return null;
        }

//        public void bringBack(Book book) {
//            if (wasBookRemoved(book)) {
//                removePermanently(book);
//                addNewBook(book);
//                return;
//            }
//            System.out.println("Book does not exist in removed list");
//        }

        public void addBookToLibrary(Book book) {
            addNewBook(book);
        }

        public void removeBookFromLibrary(Book book) {
            removeBook(book);
        }
    }

}
