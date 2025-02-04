package StudentSystem;

import java.util.ArrayList;
import java.util.List;

public class School {
    private ArrayList<Student> students;
    private ArrayList<Course> courses;

    public School() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        initializeCourses();
    }

    // method to pre-populate courses
    private void initializeCourses() {
        courses.add(new Course("CCS2304", "Advanced Programming", 0.0, "Dr. Hossam"));
        courses.add(new Course("EBA3202", "Differential Equations", 0.0, "Dr. Asmaa"));
        courses.add(new Course("CCS3402", "Theory of computation", 0.0, "Dr. Radwa"));
        courses.add(new Course("CCS2103", "Computer Architecture", 0.0, "Dr. Habrouk"));
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

    public List<Course> getCourses() {
        return courses;
    }

//    @Override
//    public String toString() {
//        StringBuilder result = new StringBuilder();
//        result.append("Students:\n");
//        for (Student student : students) {
//            result.append(student.toString()).append("\n");
//        }
//
//        result.append("\nAvailable Courses:\n");
//        for (Course course : courses) {
//            result.append(course.toString()).append("\n");
//        }
//
//        return result.toString();
//    }

}