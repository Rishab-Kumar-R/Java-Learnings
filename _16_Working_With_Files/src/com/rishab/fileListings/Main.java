package com.rishab.fileListings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        Path path = Path.of("");
        System.out.println("current working directory: " + path.toAbsolutePath());

        try (Stream<Path> paths = Files.list(path)) {
            paths
                .map(Main::listDirectory)
                .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("—".repeat(50));
        // walk() is a recursive method that lists all the files and directories in the given path
        try (Stream<Path> paths = Files.walk(path, 2)) {
            paths
                .filter(Files::isRegularFile)
                .map(Main::listDirectory)
                .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("—".repeat(50));
        // walk() is a recursive method that lists all the files and directories in the given path
        try (Stream<Path> paths = Files.find(path, Integer.MAX_VALUE,
            (p, a) -> a.isRegularFile() && a.size() > 300)) {
            paths
                .map(Main::listDirectory)
                .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("———————————————Directory Stream———————————————");
        path = path.resolve(".idea");
        try (var dirs = Files.newDirectoryStream(path, "*.xml")) {
            dirs.forEach(dir -> System.out.println(Main.listDirectory(dir)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("———————————————Directory Stream———————————————");
        try (var dirs = Files.newDirectoryStream(path,
            p -> p.getFileName().toString().endsWith("xml") && Files.isRegularFile(p) && Files.size(p) > 1000)) {
            dirs.forEach(dir -> System.out.println(Main.listDirectory(dir)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static String listDirectory(Path path) {
        try {
            boolean isDirectory = Files.isDirectory(path);
            FileTime lastModifiedTime = Files.getLastModifiedTime(path);
            LocalDateTime modifiedDateTime = LocalDateTime.ofInstant(
                lastModifiedTime.toInstant(), ZoneId.systemDefault());
            return "%tD %tT %-5s %12s %s".formatted(modifiedDateTime, modifiedDateTime,
                (isDirectory ? "<DIR>" : ""), (isDirectory ? "" : Files.size(path)), path);
        } catch (IOException e) {
            System.out.println("Error!! Something went wrong while checking if the " + path + " is a directory or not");
            return path.toString();
        }
    }
}
