package StudentSystem;

import java.util.ArrayList;
import java.util.List;

public class School {
    private ArrayList<Student> students;
    private ArrayList<Course> courses;

    public School() {
        students = new ArrayList<>();
        courses = new ArrayList<>();

        // Optional: Pre-populate some courses
        initializeCourses();
    }

    // Optional method to pre-populate courses
    private void initializeCourses() {
        courses.add(new Course("CS101", "Introduction to Programming", 0.0, "Dr. Smith"));
        courses.add(new Course("MATH201", "Calculus", 0.0, "Prof. Johnson"));
        courses.add(new Course("ENG102", "English Composition", 0.0, "Ms. Williams"));
        courses.add(new Course("PHYS101", "Physics Fundamentals", 0.0, "Dr. Brown"));
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void deleteStudent(String studentId) {
        students.removeIf(student -> student.getId().equals(studentId));
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    // New methods for course management
    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(String courseCode) {
        courses.removeIf(course -> course.getCourseCode().equals(courseCode));
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Course findCourseByCode(String courseCode) {
        return courses.stream()
                .filter(course -> course.getCourseCode().equals(courseCode))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Students:\n");
        for (Student student : students) {
            result.append(student.toString()).append("\n");
        }

        result.append("\nAvailable Courses:\n");
        for (Course course : courses) {
            result.append(course.toString()).append("\n");
        }

        return result.toString();
    }
}