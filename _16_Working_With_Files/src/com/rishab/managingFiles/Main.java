package com.rishab.managingFiles;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

//        File oldFile = new File("students.json");
//        File newFile = new File("student-activity.json");
//
//        if (oldFile.exists()) {
//            oldFile.renameTo(newFile);
//            System.out.println("File renamed successfully");
//        } else {
//            System.out.println("File does not exist");
//        }

//        Path oldPath = Path.of("students.json");
//        Path newPath = Path.of("files/student-activity.json");
//
//        try {
//            Files.createDirectories(newPath.subpath(0, newPath.getNameCount() - 1));
//            Files.move(oldPath, newPath);
//            System.out.println(oldPath + " renamed to " + newPath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Path fileDir = Path.of("files");
        Path resourceDir = Path.of("resources");

        try {
//            shallow copy
//            Files.copy(fileDir, resourceDir);

//            Files.deleteIfExists(resourceDir);
            recurseDelete(resourceDir);
            recurseCopy(fileDir, resourceDir);
            System.out.println("Directory copied successfully to " + resourceDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        copying the data from one file to another, but Files.copy() is better while copying files
//        reader.transferTo(writer) is a better way to copy large files
//        try (BufferedReader reader = new BufferedReader(new FileReader("files/student-activity.json"));
//            PrintWriter writer = new PrintWriter("student-backup.json")) {
//            reader.transferTo(writer);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        String urlString = "https://api.census.gov/data/2019/pep/charagegroups?get=NAME,POP&for=state:*";
        URI uri = URI.create(urlString);
        try (var urlInputStream = uri.toURL().openStream()) {
            urlInputStream.transferTo(System.out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Path jsonPath = Path.of("USPopulationByState.txt");
        try (var reader = new InputStreamReader(uri.toURL().openStream());
             var writer = Files.newBufferedWriter(jsonPath)) {
            reader.transferTo(writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (var reader = new InputStreamReader(uri.toURL().openStream());
             PrintWriter writer = new PrintWriter("USPopulationByState.csv")) {
            reader.transferTo(new Writer() {

                @Override
                public void write(char[] cbuf, int off, int len) throws IOException {
                    String jsonString = new String(cbuf, off, len).trim();
                    jsonString = jsonString.replace('[', ' ').trim();
                    jsonString = jsonString.replaceAll("\\]", "").trim();
                    writer.write(jsonString);
                }

                @Override
                public void flush() throws IOException {
                    writer.flush();
                }

                @Override
                public void close() throws IOException {
                    writer.close();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void recurseCopy(Path source, Path destination) throws IOException {
//        making a shallow copy
        Files.copy(source, destination, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        if (Files.isDirectory(source)) {
            try (var children = Files.list(source)) {
                children.toList().forEach(child -> {
                    try {
                        Main.recurseCopy(child, destination.resolve(child.getFileName()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }

    private static void recurseDelete(Path destination) throws IOException {
        if (Files.isDirectory(destination)) {
            try (var children = Files.list(destination)) {
                children.toList().forEach(child -> {
                    try {
                        Main.recurseDelete(child);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
        Files.delete(destination);
    }
}
