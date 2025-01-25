package StudentSystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class studentViewPage {
    protected JPanel mainPanel;

    public studentViewPage(JFrame frame, School school) {
        // Initialize main panel with a more sophisticated layout
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Create a styled header
        JLabel headerLabel = new JLabel("Student Records", SwingConstants.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setForeground(new Color(29, 59, 85));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Create a text area with custom styling
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setBackground(new Color(250, 250, 250));
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Check if there are students in the school
        List<Student> students = school.getStudents();
        if (students == null || students.isEmpty()) {
            textArea.setText("Error: No student data available.");
        } else {
            StringBuilder displayText = new StringBuilder();

            for (Student student : students) {
                // Add student header with formatting
                displayText.append("═══════════════════════════════════════════\n");
                displayText.append(String.format("Student ID: %s\n", student.getId()));
                displayText.append(String.format("Name: %s\n", student.getName()));
                displayText.append(String.format("Age: %d\n", student.getAge()));
                displayText.append("Courses:\n");

                List<Course> courses = student.getCourses();
                if (courses.isEmpty()) {
                    displayText.append("   No courses registered\n");
                } else {
                    for (Course course : courses) {
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
                displayText.append("\n");
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

        // Create a button panel for multiple buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

        // Back button with styling
        JButton backButton = new JButton("Back to Home");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBackground(new Color(29, 59, 85));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            frame.setContentPane(new studentHomePage(frame).mainPanel);
            frame.revalidate();
        });

        // Add buttons to button panel
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Set the frame content
        if (frame != null) {
            frame.setContentPane(mainPanel);
            frame.revalidate();
        } else {
            throw new IllegalArgumentException("Frame cannot be null");
        }
    }
}
