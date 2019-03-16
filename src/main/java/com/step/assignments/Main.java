package com.step.assignments;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Library library = new Library();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to library.");

        while (true) {
            System.out.println("Enter Choice.");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Reader Name to insert");
                    String readerName = scanner.nextLine();
                    library.addReader(new BookReader(readerName));
//                    System.out.println(wasSuccessfull ? "Added Successfully" : "Already Exist");
                    System.out.println("Added");
                    break;

                case 2:
                    System.out.println("Book Name to insert");
                    String bookname = scanner.nextLine();
//                    wasSuccessfull =
                    Library.Librarian librarian = library.getLibrarian();
                    librarian.addBookToLibrary(new Book(bookname));
//                    System.out.println(wasSuccessfull ? "Added Successfully" : "Already Exist");
                    System.out.println("Added");
                    break;

                case 3:
                    System.out.println("Showing reader's register");
                    System.out.println(library);
                    break;

                default:
                    System.out.println("Quitting Library");
                    System.exit(0);
            }
        }
    }
}