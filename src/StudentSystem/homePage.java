package StudentSystem;
// File: homePage.java

import javax.swing.*;
import java.awt.*;

public class homePage {
    public JPanel mainPanel;

    public homePage(JFrame frame) {
        // Customize the main panel with the gradient background
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();

                // Gradient background with student management system vibes
                Color color1 = new Color(64, 131, 190); // Light blue
                Color color2 = new Color(29, 59, 85);   // Deep navy blue
                GradientPaint gradient = new GradientPaint(0, 0, color1, width, height, color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, width, height);
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // Add the label for Student Management System
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(64, 131, 190)); // Light blue background
        JLabel titleLabel = new JLabel("Student Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Create the panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10)); // 2 rows, 2 columns with spacing
        buttonPanel.setOpaque(false); // Allow gradient background to show through

        // Create the buttons with deep navy blue background
        JButton viewButton = createStyledButton("View");
        JButton editButton = createStyledButton("Edit");
        JButton addButton = createStyledButton("Add");
        JButton deleteButton = createStyledButton("Delete");

        // Add buttons to the panel
        buttonPanel.add(viewButton);
        buttonPanel.add(editButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Set functionality for buttons (replace with actual navigation as needed)
        viewButton.addActionListener(e -> System.out.println("Navigating to View page..."));
        editButton.addActionListener(e -> System.out.println("Navigating to Edit page..."));
        addButton.addActionListener(e -> System.out.println("Navigating to Add page..."));
        deleteButton.addActionListener(e -> System.out.println("Navigating to Delete page..."));

        // Set the frame content pane to the main panel
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400); // Adjusted size for home page
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    // Helper method to create a styled button
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(29, 59, 85)); // Deep navy blue
        button.setForeground(Color.WHITE); // White text for contrast
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}
