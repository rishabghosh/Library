package com.step.assignments;

public class Book {
    private final String name;
    private final long id;

    public Book(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public Book(String name){
        this.name = name;
        this.id = (long) Math.random();
    }

    public String getName() {
        return this.name;
    }

    public long getId() {
        return this.id;
    }
}
