

import java.util.ArrayList;
import java.util.Scanner;



public class Exercise1 {
    public static class Book {
        int id;
        String title;
        String author;
        double price;

        Book(int id, String title, String author, double price) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.price = price;
        }

        void Print() {
            System.out.printf("- ID: %d\n- Title: %s\n- Author: %s\n- Price: %f", this.id, this.title, this.author, this.price);
        }
    }
    public static void main(String[] args) throws Exception { 
        ArrayList <Book> books = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.print("Please Enter Number of Books: ");
        int num = input.nextInt();
        System.out.println("=".repeat(30));
        for(int i = 0; i < num; i ++) {
            System.out.println("+ Book " + (i + 1) + ":");
            System.out.print("Please Enter Book ID: ");
            int id = input.nextInt();
            input.nextLine();
            System.out.print("Please Enter Book Title: ");
            String title = input.nextLine();
            System.out.print("Please Enter Author Name: ");
            String author = input.nextLine();
            System.out.print("Please Enter Book Price: ");
            double price = input.nextDouble();
            books.add(new Book(id, title, author, price));
            input.nextLine();
        }
        input.close();
        System.out.println("=".repeat(30));

        int i = 1;
        double avg = 0;
        System.out.println("=".repeat(30));
        for(Book book : books) {
            System.out.printf("Book %d:\n", i);
            book.Print();
            System.out.println();
            avg += book.price;
            i ++;
        }
        System.out.println("=".repeat(30));
        System.out.printf("+ Average Price: %f", (avg / books.size()));
        System.out.println("\n=".repeat(30));
    }
}
