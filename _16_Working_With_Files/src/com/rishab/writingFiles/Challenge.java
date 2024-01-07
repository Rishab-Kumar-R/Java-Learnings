package com.rishab.writingFiles;

import com.rishab.writingFiles.student.Course;
import com.rishab.writingFiles.student.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Challenge {
    public static void main(String[] args) {

        Course java = new Course("Java", "Java Programming Masterclass for Software Developers");
        Course python = new Course("Python", "Complete Python Bootcamp: Go from zero to hero in Python 3");

        String delimiter = "," + System.lineSeparator();
        String student = Stream
            .generate(() -> Student.getRandomStudent(java, python))
            .limit(1000)
            .map(Student::toJSON)
            .collect(Collectors.joining(delimiter, "[", "]"));

        System.out.println(student);

        try {
            Files.writeString(Path.of("students.json"), student);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
