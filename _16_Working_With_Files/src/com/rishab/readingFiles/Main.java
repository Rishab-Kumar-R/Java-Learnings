package com.rishab.readingFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try (FileReader fileReader = new FileReader("file.txt")) {
            char[] block = new char[1000];
            int data;
            while ((data = fileReader.read(block)) != -1) {
                String content = new String(block, 0, data);
                System.out.printf("---> [%d chars] %s%n", data, content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("—".repeat(50));
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("file.txt"))) {
            bufferedReader.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
