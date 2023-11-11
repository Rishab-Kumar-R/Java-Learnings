package university.student;

public class Student {
    private final String studentName;
    private final int studentId;

    public Student(String studentName, int studentId) {
        this.studentName = studentName;
        this.studentId = studentId;
    }

    public void printStudentDetails() {
        System.out.println("Student Name: " + studentName);
        System.out.println("Student ID: " + studentId);
    }

    public String getStudentName() {
        return studentName;
    }
}
