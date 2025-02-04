package StudentSystem;
public class Course {
    private String courseCode;
    private String courseName;
    private double grade;
    private String instructor;

    public Course(String courseCode, String courseName, double grade, String instructor) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.grade = grade;
        this.instructor = instructor;
    }

    // Getters
    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getGrade() {
        return grade;
    }

    public String getInstructor() {
        return instructor;
    }

    @Override
    public String toString() {
        return courseCode + " - " + courseName + " (Grade: " + grade + ", Instructor: " + instructor + ")";
    }
}
