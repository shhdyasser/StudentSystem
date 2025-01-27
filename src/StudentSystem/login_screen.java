
package StudentSystem;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class login_screen {
    public JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JFrame frame;
    private Map<String, String> studentCredentials;

    public login_screen(JFrame frame) {
        this.frame = frame;
        initializeStudentCredentials();
        initComponents();

        // Customize the main panel with a gradient background
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
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add a title label
        JLabel title = new JLabel("Login");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 35));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(title, gbc);

        // Add username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(usernameLabel, gbc);

        // Add username text field
        usernameField = new JTextField(15);
        usernameField.setBackground(new Color(132, 150, 169));
        usernameField.setForeground(Color.BLACK);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(usernameField, gbc);

        // Add password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(passwordLabel, gbc);

        // Add password field
        passwordField = new JPasswordField(15);
        passwordField.setBackground(new Color(132, 150, 169));
        passwordField.setForeground(Color.BLACK);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(passwordField, gbc);

        // Add login button
        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(62, 92, 116));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, gbc);

        // Add functionality to the login button
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (username.equals("admin") && password.equals("password")) {
                frame.dispose();
                openAdminHomePage();
            } else if (username.equals("student") && password.equals("password")) {
                String studentId = studentCredentials.get(username);
                frame.dispose();
                openStudentHomePage(studentId);
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Invalid credentials. Please try again.");
            }
        });
    }

    private void initializeStudentCredentials() {
        studentCredentials = new HashMap<>();
        studentCredentials.put("student", "N001");  // Default student login maps to Ali's ID
    }

    private void openAdminHomePage() {
        JFrame homeFrame = new JFrame("Admin Home Page");
        homePage homePageScreen = new homePage(homeFrame);
        homeFrame.setContentPane(homePageScreen.mainPanel);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setSize(600, 400);
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setVisible(true);
    }

    private void openStudentHomePage(String studentId) {
        JFrame homeFrame = new JFrame("Student Home Page");
        studentHomePage studentHomePageScreen = new studentHomePage(homeFrame, studentId);
        homeFrame.setContentPane(studentHomePageScreen.mainPanel);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setSize(600, 400);
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setVisible(true);
    }

    private void initComponents() {
        mainPanel = new JPanel();
    }

    public static void main(String[] args) {
        JFrame loginFrame = new JFrame("Student Management System - Login");
        login_screen loginScreen = new login_screen(loginFrame);
        loginFrame.setContentPane(loginScreen.mainPanel);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 400);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }
}