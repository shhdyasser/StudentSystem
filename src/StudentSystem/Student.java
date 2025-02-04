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
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    // Course management
    public void addCourse(Course course) {
        courses.add(course);
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
