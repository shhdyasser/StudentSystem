package StudentSystem;

import java.util.ArrayList;

public abstract class Student {
    private String name;
    private String id;
    private int age;
    private ArrayList<Course> courses;

    public Student(String name, String id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.courses = new ArrayList<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    // Course management
    public void addCourse(Course course) {
        courses.add(course);
    }

    public void editCourse(String courseCode, Course updatedCourse) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseCode().equals(courseCode)) {
                courses.set(i, updatedCourse);
                return;
            }
        }
        System.out.println("Course not found!");
    }

    public void deleteCourse(String courseCode) {
        courses.removeIf(course -> course.getCourseCode().equals(courseCode));
    }

    @Override
    public String toString() {
        String result = "ID: " + id + ", Name: " + name + ", Age: " + age + "\nCourses:\n";
        for (Course course : courses) {
            result += " - " + course + "\n";
        }
        return result;
    }
}
