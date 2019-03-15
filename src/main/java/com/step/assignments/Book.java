package com.step.assignments;

public class Book {
    private final String name;
    private final long id;

    public Book(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public long getId() {
        return this.id;
    }
}
