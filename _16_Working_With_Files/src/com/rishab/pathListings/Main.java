package com.rishab.pathListings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {

        // Path path = Path.of("files/testing.txt");
        Path path = Path.of("this/is/several/folders/testing.txt");

        // printPathInfo(path);
        logStatement(path);
        extraInfo(path);

    }

    private static void printPathInfo(Path path) {
        System.out.println("Path: " + path); // files/testing.txt
        System.out.println("fileName: " + path.getFileName()); // testing.txt
        System.out.println("parent: " + path.getParent()); // files
        System.out.println("absolutePath: " + path.toAbsolutePath()); // /home/rishab/IdeaProjects/Java-Files-API/files/testing.txt
        System.out.println("absolutePath root: " + path.toAbsolutePath().getRoot()); // /
        System.out.println("root: " + path.getRoot()); // null (relative path)
        System.out.println("isAbsolute: " + path.isAbsolute()); // false (relative path)

        System.out.println(path.toAbsolutePath().getRoot());
        // int i = 1;
        // var iterator = path.toAbsolutePath().iterator();

        // while (iterator.hasNext()) {
        //     System.out.println(".".repeat(i++) + " " + iterator.next());
        // }

        int pathParts = path.toAbsolutePath().getNameCount();
        for (int i = 0; i < pathParts; i++) {
            System.out.println(".".repeat(i + 1) + " " + path.toAbsolutePath().getName(i));
        }

        System.out.println("—".repeat(50));
    }

    private static void logStatement(Path path) {
        try {
            Path parent = path.getParent();
            if (!Files.exists(parent)) {
                Files.createDirectories(parent);
            }
            Files.writeString(path, Instant.now() + ": hello from Java Files API\n",
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void extraInfo(Path path) {
        try {
            var attributes = Files.readAttributes(path, "*");
            attributes.entrySet().forEach(System.out::println);

            System.out.println(Files.probeContentType(path));
        } catch (IOException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}
