package com.rishab.managingFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Challenge {
    public static void main(String[] args) {

        Path deepestFolder = Path.of("public", "assets", "images");

        try {
            Files.createDirectories(deepestFolder);
            generateIndexFile(deepestFolder.getName(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 1; i <= deepestFolder.getNameCount(); i++) {
            Path indexedPath = deepestFolder.subpath(0, i).resolve("index.txt");
            Path backupPath = deepestFolder.subpath(0, i).resolve("indexCopy.txt");
            try {
                Files.copy(indexedPath, backupPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            generateIndexFile(deepestFolder.getName(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void generateIndexFile(Path path) throws IOException {
        Path indexFile = path.resolve("index.txt");
        try (Stream<Path> contents = Files.find(path, Integer.MAX_VALUE, (p, a) -> true)) {
            String fileContents = contents
                .map(p -> p.toAbsolutePath().toString())
                .collect(Collectors.joining(
                    System.lineSeparator(), "Directory contents: " + System.lineSeparator(),
                    System.lineSeparator() + "Generated: " + LocalDateTime.now()));
            Files.writeString(indexFile, fileContents, StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Stream<Path> contents = Files.list(path)) {
            contents
                .filter(Files::isDirectory)
                .toList()
                .forEach(dir -> {
                    try {
                        generateIndexFile(dir);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        }
    }
}
