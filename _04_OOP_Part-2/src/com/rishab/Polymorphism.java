package com.rishab;

import java.util.Scanner;

public class Polymorphism {
    public static void main(String[] args) {

        // Movie movie = new Movie("Interstellar");
        // movie.watchMovie();

        // Movie getMovie = Movie.getMovie("science", "Gravity");
        // getMovie.watchMovie();

        // Movie adventure = new Adventure("Jumanji");
        // adventure.watchMovie();

        // Movie comedy = new Comedy("The Hangover");
        // comedy.watchMovie();

        // Movie scienceFiction = new ScienceFiction("Inception");
        // scienceFiction.watchMovie();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the type of movie you want to watch (A/C/S/Q): ");
            String type = scanner.nextLine();

            if ("Qq".contains(type)) {
                break;
            }

            System.out.print("Enter the title of the movie: ");
            String title = scanner.nextLine();
            Movie movie = Movie.getMovie(type, title);
            movie.watchMovie();

        }

    }
}

class Movie {
    private String title;

    public Movie(String title) {
        this.title = title;
    }

    public void watchMovie() {
        String instanceType = this.getClass().getSimpleName();
        System.out.println("Watching " + instanceType + " - " + this.title);
    }

    // Factory Function - A function that returns an instance of a class
    public static Movie getMovie(String type, String title) {
        return switch (type.toUpperCase().charAt(0)) {
            case 'A' -> new Adventure(title);
            case 'C' -> new Comedy(title);
            case 'S' -> new ScienceFiction(title);
            default -> new Movie(title);
        };
    }
}

class Adventure extends Movie {
    public Adventure(String title) {
        super(title);
    }

    @Override
    public void watchMovie() {
        super.watchMovie();
        System.out.printf("    ...%s%n".repeat(3), "Pleasant", "Enjoyable", "Thrilling");
    }

    public void watchAdventureMovie() {
        System.out.println("Watching Adventure Movie");
    }
}

class Comedy extends Movie {
    public Comedy(String title) {
        super(title);
    }

    @Override
    public void watchMovie() {
        super.watchMovie();
        System.out.printf("    ...%s%n".repeat(3), "Funny", "Hilarious", "Laughable");
    }

    public void watchComedyMovie() {
        System.out.println("Watching Comedy Movie");
    }
}

class ScienceFiction extends Movie {
    public ScienceFiction(String title) {
        super(title);
    }

    @Override
    public void watchMovie() {
        super.watchMovie();
        System.out.printf("    ...%s%n".repeat(3), "Futuristic", "Imaginative", "Innovative");
    }

    public void watchScienceFictionMovie() {
        System.out.println("Watching ScienceFiction Movie");
    }
}
