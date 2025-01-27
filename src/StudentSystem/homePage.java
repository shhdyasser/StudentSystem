package StudentSystem;

import javax.swing.*;
import java.awt.*;

public class homePage {
    public JPanel mainPanel;
    private School school;

    public homePage(JFrame frame) {
        // Get the school instance
        this.school = Main.getSchoolInstance();

        // Customize the main panel with the gradient background
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();

                Color color1 = new Color(64, 131, 190);
                Color color2 = new Color(29, 59, 85);
                GradientPaint gradient = new GradientPaint(0, 0, color1, width, height, color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, width, height);
            }
        };
        mainPanel.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(64, 131, 190));
        JLabel titleLabel = new JLabel("Admin Dashboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        mainPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
        buttonPanel.setOpaque(false);

        JButton viewButton = createStyledButton("View Students");
        JButton editButton = createStyledButton("Edit Students");
        JButton addButton = createStyledButton("Add Student");
        JButton deleteButton = createStyledButton("Delete Student");

        buttonPanel.add(viewButton);
        buttonPanel.add(editButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add logout button
        JButton logoutButton = createStyledButton("Logout");
        logoutButton.setPreferredSize(new Dimension(100, 40));
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.setOpaque(false);
        logoutPanel.add(logoutButton);
        mainPanel.add(logoutPanel, BorderLayout.SOUTH);

        // Button Actions
        viewButton.addActionListener(e -> {
            frame.setContentPane(new AdminViewPage(frame, school).mainPanel);
            frame.revalidate();
        });

        editButton.addActionListener(e -> {
            frame.setContentPane(new AdminViewPage(frame, school).mainPanel);
            frame.revalidate();
        });

        addButton.addActionListener(e -> {
            frame.setContentPane(new AdminViewPage(frame, school).mainPanel);
            frame.revalidate();
        });

        deleteButton.addActionListener(e -> {
            frame.setContentPane(new AdminViewPage(frame, school).mainPanel);
            frame.revalidate();
        });

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

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(29, 59, 85));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}