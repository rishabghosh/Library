package com.step.assignments;

import java.util.HashSet;
import java.util.Set;

public class Library {
    private final Set<Book> books = new HashSet<>();
    private final Set<BookReader> bookReaders = new HashSet<>();
    private final Set<Book> removedBooks = new HashSet<>();

    public Library() {}


    private boolean isSameBook(Book book, String nameOfBook) {
        return book.getName().equals(nameOfBook);
    }

    public boolean hasBook(Book book) {
        return this.books.contains(book);
    }

    public void addNewBook(Book newBook) {
        this.books.add(newBook);
    }

    public void removeBook(Book book) {
        if (hasBook(book)) {
            this.books.remove(book);
            this.removedBooks.add(book);
            return;
        }
        System.out.printf("Book %s does not exist", book.getName());
    }

    public boolean searchByName(String nameOfBook) {
        for (Book book : this.books) {
            if (isSameBook(book, nameOfBook)) {
                return true;
            }
        }
        return false;
    }

    public Set<Book> getBooks() {
        Set<Book> copyOfBooks = new HashSet<>(this.books);
        return copyOfBooks;
    }



    public void addReader(BookReader reader) {
        this.bookReaders.add(reader);
    }

    public Set<BookReader> getBookReaders() {
        return new HashSet<>(this.bookReaders);
    }

    public Set<Book> getRemovedBooks() {
        return new HashSet<>(this.removedBooks);
    }

    public void removePermanently(Book book){
        this.removedBooks.remove(book);
    }
}
