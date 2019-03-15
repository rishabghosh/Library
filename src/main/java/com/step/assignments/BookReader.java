package com.step.assignments;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookReader {
    private final String name;
    private final Set<Book> borrowedBooks = new HashSet<>();

    public BookReader(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void borrowBook(Book book){
        this.borrowedBooks.add(book);
    }

    public boolean hasBorrowed(Book book) {
        return this.borrowedBooks.contains(book);
    }

    public void returnBook(Book book){
        if (hasBorrowed(book)){
            this.borrowedBooks.remove(book);
            return;
        }
        System.out.printf("Book %s does not exist", book.getName());
    }

    public boolean searchBook(List<Book> books, String nameOfBook){
        for (Book book:books) {
            if(book.getName().equals(nameOfBook)) return true;
        }
        return false;
    }

    public Set<Book> getBorrowedBooks() {
        Set<Book> copyOfBooks = new HashSet<>(this.borrowedBooks);
        return copyOfBooks;
    }

}
