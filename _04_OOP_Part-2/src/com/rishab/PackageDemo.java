package com.rishab;

import university.course.Course;
import university.student.Student;

public class PackageDemo {
    public static void main(String[] args) {
        Student student = new Student("Charlie", 21);
        student.printStudentDetails();

        Course course = new Course("Introduction to Computer Science", 101);
        course.printCourseDetails();

        System.out.println(student.getStudentName() + " is enrolled in " + course.getCourseName());
    }
}
