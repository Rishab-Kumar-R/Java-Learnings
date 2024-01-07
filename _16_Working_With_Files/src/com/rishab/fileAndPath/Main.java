package com.rishab.fileAndPath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        useFile("testing.txt");
        usePath("pathfile.txt");
    }

    private static void useFile(String fileName) {
        File file = new File(fileName);
        boolean fileExists = file.exists();

        System.out.printf("File '%s' %s%n", fileName, fileExists ? "exists" : "does not exist");

        if (fileExists) {
            System.out.println("Deleting file..." + fileName);
            fileExists = !file.delete(); // delete() returns true if file was deleted successfully else false
        }

        if (!fileExists) {
            System.out.println("Creating file..." + fileName);
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
                e.printStackTrace();
            }
            if (file.canWrite()) {
                System.out.println("Writing to file...");
            }
        }
    }

    private static void usePath(String fileName) {
        Path path = Path.of(fileName);
        boolean fileExists = Files.exists(path);

        System.out.printf("File '%s' %s%n", fileName, fileExists ? "exists" : "does not exist");

        if (fileExists) {
            System.out.println("Deleting file..." + fileName);
            try {
                Files.delete(path);
                fileExists = false;
            } catch (IOException e) {
                System.out.println("Error deleting file: " + e.getMessage());
                e.printStackTrace();
            }
        }

        if (!fileExists) {
            System.out.println("Creating file..." + fileName);
            try {
                Files.createFile(path);
                if (Files.isWritable(path)) {
                    Files.writeString(path, """
                        Here is some data,
                        that I am writing to a file,
                        using the Files class.
                        """);
                }
                System.out.println("—".repeat(50));
                System.out.println("Reading file...");
                Files.readAllLines(path).forEach(System.out::println);
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
