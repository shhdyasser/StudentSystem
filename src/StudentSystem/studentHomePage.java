package StudentSystem;

import javax.swing.*;
import java.awt.*;

public class studentHomePage {
    public JPanel mainPanel;
    private String studentId;

    public studentHomePage(JFrame frame, String studentId) {
        this.studentId = studentId;

        // Initialize the main panel
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();

                // Gradient background
                Color color1 = new Color(64, 131, 190);
                Color color2 = new Color(29, 59, 85);
                GradientPaint gradient = new GradientPaint(0, 0, color1, width, height, color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, width, height);
            }
        };
        mainPanel.setLayout(new GridLayout(3, 1, 10, 10));

        // Create a label for the title
        JLabel titleLabel = new JLabel("Student Home Page", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        mainPanel.add(titleLabel);

        // Create an enlarged "View" button
        JButton viewButton = new JButton("View My Record");
        viewButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        viewButton.setBackground(new Color(62, 92, 116));
        viewButton.setForeground(Color.WHITE);
        viewButton.setPreferredSize(new Dimension(150, 50));
        mainPanel.add(viewButton);

        // Add functionality to the "View" button
        viewButton.addActionListener(e -> {
            frame.dispose();
            JFrame viewFrame = new JFrame("Student View Page");
            studentViewPage viewPage = new studentViewPage(viewFrame, Main.getSchoolInstance(), studentId);
            viewFrame.setContentPane(viewPage.mainPanel);
            viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            viewFrame.setSize(600, 400);
            viewFrame.setLocationRelativeTo(null);
            viewFrame.setVisible(true);
        });

        // Create a "Logout" button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        logoutButton.setBackground(new Color(170, 50, 50));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setPreferredSize(new Dimension(120, 40));
        mainPanel.add(logoutButton);

        // Add functionality to the "Logout" button
        logoutButton.addActionListener(e -> {
            frame.dispose();
            JFrame loginFrame = new JFrame("Student Management System - Login");
            login_screen loginScreen = new login_screen(loginFrame);
            loginFrame.setContentPane(loginScreen.mainPanel);
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loginFrame.setSize(400, 400);
            loginFrame.setLocationRelativeTo(null);
            loginFrame.setVisible(true);
        });
    }
}