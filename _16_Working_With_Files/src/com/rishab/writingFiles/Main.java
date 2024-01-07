package com.rishab.writingFiles;

import com.rishab.writingFiles.student.Course;
import com.rishab.writingFiles.student.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        String header = """
            Student Id,Country Code,Enrolled Year,Age,Gender,Experienced,Course Code,Engagement Month,\
            Engagement Year,Engagement Type""";

        Course java = new Course("Java", "Java Programming Masterclass for Software Developers");
        Course python = new Course("Python", "Complete Python Bootcamp: Go from zero to hero in Python 3");

        List<Student> studentList = Stream
            .generate(() -> Student.getRandomStudent(java, python))
            .limit(25)
            .toList();

        // System.out.println(header);
        // studentList.forEach(s -> s.getEngagementRecords().forEach(System.out::println));

        Path path = Path.of("students.csv");
        // try {
        //     Files.writeString(path, header + "\n");
        //     for(Student student : studentList) {
        //         Files.write(path, student.getEngagementRecords(), StandardOpenOption.APPEND);
        //     }
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        // try {
        //     List<String> data = new ArrayList<>();
        //     data.add(header);
        //     for (Student student : studentList) {
        //         data.addAll(student.getEngagementRecords());
        //     }
        //     Files.write(path, data);
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        try (BufferedWriter bw = Files.newBufferedWriter(Path.of("take2.csv"))) {
            bw.write(header);
            bw.newLine();
            int count = 0;
            for (Student student : studentList) {
                for (var record : student.getEngagementRecords()) {
                    bw.write(record + "\n");
                    count++;
                    if (count % 5 == 0) {
                        Thread.sleep(2000);
                        System.out.print(".");
                    }
                    if (count % 10 == 0) {
                        bw.flush();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter fw = new FileWriter("take3.csv")) {
            fw.write(header);
            fw.write(System.lineSeparator());
            for (Student student : studentList) {
                for (var record : student.getEngagementRecords()) {
                    fw.write(record);
                    fw.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter pw = new PrintWriter("take4.txt")) {
            pw.println(header);
            for (Student student : studentList) {
                for (var record : student.getEngagementRecords()) {
                    String[] recordData = record.split(",");
                    pw.printf("%-12d%-5s%2d%4d%3d%-1s".formatted(
                        student.getStudentId(), // student id
                        student.getCountry(), // country code
                        student.getEnrollmentYear(), // enrollment year
                        student.getEnrollmentMonth(), // enrollment month
                        student.getEnrollmentAge(), // age
                        student.getGender() // gender
                    ));
                    pw.printf("%-1s", (student.hasExperience() ? 'Y' : 'N')); // experienced
                    pw.format("%-3s%10.2f%-10s%-4s%-30s".formatted(
                        recordData[7], // course code
                        student.getPercentComplete(recordData[7]), // percent complete
                        recordData[8], // engagement month
                        recordData[9], // engagement year
                        recordData[10] // engagement type
                    ));
                    pw.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
