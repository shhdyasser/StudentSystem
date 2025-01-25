package StudentSystem;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // First, create and set up the data model
        School school = createAndPopulateSchool();

        // Launch the GUI
        SwingUtilities.invokeLater(() -> {
            // Create and set up the main window
            JFrame frame = new JFrame("Student Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            // Pass the school object to the studentViewPage
            new studentViewPage(frame, school);  // Pass the entire school object

            // Center and show the window
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
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
