package StudentSystem;

import javax.swing.*;
import java.awt.*;

public class studentViewPage {
    protected JPanel mainPanel;
    private School school;
    private String studentId; // Add this to store the logged-in student's ID

    public studentViewPage(JFrame frame, School school, String studentId) { // Modified constructor
        this.school = school;
        this.studentId = studentId;

        // Initialize main panel with a more sophisticated layout
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Create a styled header
        JLabel headerLabel = new JLabel("Student Info ", SwingConstants.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setForeground(new Color(29, 59, 85));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Create a text area with custom styling
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setBackground(new Color(250, 250, 250));
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Find the logged-in student
        Student currentStudent = null;
        for (Student student : school.getStudents()) {
            if (student.getId().equals(studentId)) {
                currentStudent = student;
                break;
            }
        }

        if (currentStudent == null) {
            textArea.setText("Error: Student record not found.");
        } else {
            StringBuilder displayText = new StringBuilder();

            // Add student information with formatting
            displayText.append("═══════════════════════════════════════════\n");
            displayText.append(String.format("Student ID: %s\n", currentStudent.getId()));
            displayText.append(String.format("Name: %s\n", currentStudent.getName()));
            displayText.append(String.format("Age: %d\n", currentStudent.getAge()));
            displayText.append("Type: ").append(currentStudent instanceof NationalStudent ? "National Student" : "International Student").append("\n");
            displayText.append("\nCourses:\n");
            displayText.append("═══════════════════════════════════════════\n");

            if (currentStudent.getCourses().isEmpty()) {
                displayText.append("   No courses registered\n");
            } else {
                for (Course course : currentStudent.getCourses()) {
                    displayText.append(String.format("   ▶ %s (%s)\n",
                            course.getCourseName(),
                            course.getCourseCode()));
                    displayText.append(String.format("      Grade: %.2f\n",
                            course.getGrade()));
                    displayText.append(String.format("      Instructor: %s\n",
                            course.getInstructor()));
                    displayText.append("\n");
                }
            }

            // Calculate and display GPA
            if (!currentStudent.getCourses().isEmpty()) {
                double totalGrade = 0;
                for (Course course : currentStudent.getCourses()) {
                    totalGrade += course.getGrade();
                }
                double gpa = (totalGrade / currentStudent.getCourses().size()) % 4;
                displayText.append("═══════════════════════════════════════════\n");
                displayText.append(String.format("Overall GPA: %.2f\n", gpa));
            }

            textArea.setText(displayText.toString());
        }

        // Create a scroll pane with custom styling
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 0, 10, 0),
                BorderFactory.createLineBorder(new Color(200, 200, 200))
        ));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Create a button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

        // Back button with styling
        JButton backButton = new JButton("Back to Home");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBackground(new Color(29, 59, 85));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            frame.dispose();
            JFrame homeFrame = new JFrame("Student Home Page");
            studentHomePage homePage = new studentHomePage(homeFrame, studentId); // Pass studentId
            homeFrame.setContentPane(homePage.mainPanel);
            homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            homeFrame.setSize(600, 400);
            homeFrame.setLocationRelativeTo(null);
            homeFrame.setVisible(true);
        });

        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
}