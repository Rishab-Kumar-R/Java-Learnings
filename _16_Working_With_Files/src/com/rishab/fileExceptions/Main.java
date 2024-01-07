package com.rishab.fileExceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Current directory: " + new File("").getAbsolutePath());
        String fileName = "files/test.csv";
        // testFile2(null);

        File file = new File(new File("").getAbsolutePath(), fileName);
        if (!file.exists()) {
            System.out.println("File does not exist");
            // System.out.println("Quitting program...");
            return;
        }
        System.out.println("File exists");

        for (File f : File.listRoots()) {
            System.out.println(f);
        }

        Path path = Paths.get("files/test.csv");
        System.out.println(file.getAbsolutePath());
        if (!Files.exists(path)) {
            System.out.println("2. File does not exist");
            // System.out.println("Quitting program...");
            return;
        }
        System.out.println("2. File exists");

    }

    private static void testFile(String fileName) {
        Path path = Paths.get(fileName);
        FileReader fileReader = null;

        // Without try-catch block (throws IOException) assuming that the file exists
        try {
            // List<String> lines = Files.readAllLines(path);
            fileReader = new FileReader(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Maybe file exists...");
        }
        System.out.println("File exists");
    }

    private static void testFile2(String fileName) {
        // try-with-resources block
        try (FileReader reader = new FileReader(fileName)) {
        } catch (FileNotFoundException e) {
            System.out.println("File '" + fileName + "' does not exist");
            throw new RuntimeException(e);
        } catch (NullPointerException | IllegalArgumentException badData) {
            System.out.println("File name is null or empty " + badData.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Something went wrong");
        } finally {
            System.out.println("Maybe file exists...");
        }
        System.out.println("File exists");
    }
}
