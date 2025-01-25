package StudentSystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class studentHomePage {
    public JPanel mainPanel;

    public studentHomePage(JFrame frame) {
        // Initialize the main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 10, 10)); // Adjusted grid for the layout

        // Create a label for the title
        JLabel titleLabel = new JLabel("Student Home Page", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(29, 59, 85));
        mainPanel.add(titleLabel);

        // Create an enlarged "View" button
        JButton viewButton = new JButton("View");
        viewButton.setFont(new Font("SansSerif", Font.BOLD, 20)); // Increased font size
        viewButton.setBackground(new Color(62, 92, 116));
        viewButton.setForeground(Color.WHITE);
        viewButton.setPreferredSize(new Dimension(150, 50)); // Increased button size
        mainPanel.add(viewButton);

        // Add functionality to the "View" button
        viewButton.addActionListener(e -> openViewPage());

        // Create a smaller "Logout" button with palette color
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        logoutButton.setBackground(new Color(170, 50, 50)); // Palette-aligned color
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setPreferredSize(new Dimension(120, 40)); // Standard size
        mainPanel.add(logoutButton);

        // Add functionality to the "Logout" button
        logoutButton.addActionListener(e -> {
            frame.dispose(); // Close the current window
            returnToLoginPage(); // Return to login page
        });
    }

    private void openViewPage() {
        // Create a new frame for the student view page
        School school = new School();
        JFrame viewFrame = new JFrame("Student View Page");
        studentViewPage viewPage = new studentViewPage(viewFrame,  school);

        // Set up the frame
        viewFrame.setContentPane(viewPage.mainPanel);
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewFrame.setSize(600, 400);
        viewFrame.setLocationRelativeTo(null); // Center the frame
        viewFrame.setVisible(true);
    }

    private void returnToLoginPage() {
        // Open the login page
        JFrame loginFrame = new JFrame("Student Management System - Login");
        login_screen loginScreen = new login_screen(loginFrame);

        // Set up the login frame
        loginFrame.setContentPane(loginScreen.mainPanel);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 400);
        loginFrame.setLocationRelativeTo(null); // Center the frame
        loginFrame.setVisible(true);
    }
}
