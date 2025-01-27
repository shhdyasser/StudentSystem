package StudentSystem;

import javax.swing.*;

public class Main {
    private static School schoolInstance;  // Add this line

    public static void main(String[] args) {
        // First, create and set up the data model
        schoolInstance = createAndPopulateSchool();  // Changed this line

        // Launch the GUI
        SwingUtilities.invokeLater(() -> {
            // Create and show the login screen
            JFrame loginFrame = new JFrame("Student Management System - Login");
            login_screen loginScreen = new login_screen(loginFrame);
            loginFrame.setContentPane(loginScreen.mainPanel);
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loginFrame.setSize(400, 400);
            loginFrame.setLocationRelativeTo(null);
            loginFrame.setVisible(true);
        });
    }

    // Add this method
    public static School getSchoolInstance() {
        if (schoolInstance == null) {
            schoolInstance = createAndPopulateSchool();
        }
        return schoolInstance;
    }

    private static School createAndPopulateSchool() {
        School school = new School();

        // Add National Student
        Student nationalStudent = new NationalStudent("Ali", "N001", 20);
        nationalStudent.addCourse(new Course("CS101", "Intro to Programming", 85, "Dr. Ahmed"));
        nationalStudent.addCourse(new Course("MA101", "Calculus I", 90, "Dr. Omar"));
        school.addStudent(nationalStudent);

        // Add IG Student
        Student igStudent = new IgStudent("Mona", "I001", 22);
        igStudent.addCourse(new Course("PH101", "Physics", 88, "Dr. Sara"));
        igStudent.addCourse(new Course("CH101", "Chemistry", 78, "Dr. Khaled"));
        school.addStudent(igStudent);

        return school;
    }
}