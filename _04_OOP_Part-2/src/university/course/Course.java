package university.course;

public class Course {
    private final String courseName;
    private final int courseId;

    public Course(String courseName, int courseId) {
        this.courseName = courseName;
        this.courseId = courseId;
    }

    public void printCourseDetails() {
        System.out.println("Course Name: " + courseName);
        System.out.println("Course ID: " + courseId);
    }

    public String getCourseName() {
        return courseName;
    }

}
