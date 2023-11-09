package com.rishab;

public class POJODemo {
    public static void main(String[] args) {

        for (int i = 1; i <= 5; i++) {
            RecordStudent student = new RecordStudent("S93200" + i,
                switch (i) {
                    case 1 -> "Alice";
                    case 2 -> "Bob";
                    case 3 -> "Charlie";
                    case 4 -> "David";
                    case 5 -> "Eve";
                    default -> "Unknown";
                }, "2000-01-01", "Math, Science, English");
            System.out.println(student);
        }

        Student pojoStudent = new Student("S932001", "Alice", "2000-01-01", "Math, Science, English");
        RecordStudent recordStudent = new RecordStudent("S932002", "Lily", "2003-08-02", "Java, Python, C++");
        System.out.println(pojoStudent);
        System.out.println(recordStudent);

        pojoStudent.setClassList(pojoStudent.getClassList() + ", History");
        // recordStudent.classList() += " , History"; // Error: cannot assign a value to final variable classList
        System.out.println(pojoStudent.getName() + " is taking " + pojoStudent.getClassList());
        System.out.println(recordStudent.name() + " is taking " + recordStudent.classList());

    }
}

// Record Class => we don't need the boilerplate code here it's Student class
// The part inside the parenthesis is the components of the record or record header
// For each component, Java generates
// 1. A field with the same name and declared type as the record component
// 2. The field is private and final, and generates public accessor methods for each component
// 3. The field is sometimes referred to as a component field
// Java generates a toString method that returns a string representation of the record
// The accessor method, for id in the example, is simply id()
record RecordStudent(String id, String name, String dateOfBirth, String classList) {
}

class Student {
    private String id;
    private String name;
    private String dateOfBirth;
    private String classList;

    public Student(String id, String name, String dateOfBirth, String classList) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.classList = classList;
    }

    @Override
    public String toString() {
        return "Student{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", dateOfBirth='" + dateOfBirth + '\'' +
            ", classList='" + classList + '\'' +
            '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getClassList() {
        return classList;
    }

    public void setClassList(String classList) {
        this.classList = classList;
    }
}
