package StudentSystem;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class AdminViewPage {
    protected JPanel mainPanel;
    private School school;

    public AdminViewPage(JFrame frame, School school) {
        this.school = school;
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Header
        JLabel headerLabel = new JLabel("Student Records Management", SwingConstants.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setForeground(new Color(29, 59, 85));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Create table
        String[] columns = {"ID", "Name", "Age", "Type", "Courses"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        // Populate table
        for (Student student : school.getStudents()) {
            String courses = student.getCourses().stream()
                    .map(Course::getCourseCode)
                    .reduce("", (a, b) -> a + ", " + b);
            if (courses.startsWith(", ")) courses = courses.substring(2);

            String type = student instanceof NationalStudent ? "National" : "International";

            model.addRow(new Object[]{
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    type,
                    courses
            });
        }

        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

        JButton backButton = createStyledButton("Back");
        JButton addButton = createStyledButton("Add Student");
        JButton editButton = createStyledButton("Edit Student");
        JButton deleteButton = createStyledButton("Delete Student");

        buttonPanel.add(backButton);
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        backButton.addActionListener(e -> {
            frame.setContentPane(new homePage(frame).mainPanel);
            frame.revalidate();
        });

        addButton.addActionListener(e -> openAddStudentDialog(frame));
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String studentId = (String) table.getValueAt(selectedRow, 0);
                openEditStudentDialog(frame, studentId);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a student to edit.");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String studentId = (String) table.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to delete this student?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    school.deleteStudent(studentId);
                    model.removeRow(selectedRow);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a student to delete.");
            }
        });
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(new Color(29, 59, 85));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private void openAddStudentDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, "Add New Student", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components
        JTextField nameField = new JTextField(20);
        JTextField idField = new JTextField(20);
        JTextField ageField = new JTextField(20);
        String[] studentTypes = {"National", "International"};
        JComboBox<String> typeCombo = new JComboBox<>(studentTypes);

        // Layout components
        gbc.gridx = 0; gbc.gridy = 0;
        dialog.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        dialog.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        dialog.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        dialog.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        dialog.add(new JLabel("Age:"), gbc);
        gbc.gridx = 1;
        dialog.add(ageField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        dialog.add(new JLabel("Type:"), gbc);
        gbc.gridx = 1;
        dialog.add(typeCombo, gbc);

        JButton saveButton = new JButton("Save");
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        dialog.add(saveButton, gbc);

        saveButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String id = idField.getText();
                int age = Integer.parseInt(ageField.getText());
                String type = (String) typeCombo.getSelectedItem();

                Student newStudent;
                if ("National".equals(type)) {
                    newStudent = new NationalStudent(name, id, age);
                } else {
                    newStudent = new IgStudent(name, id, age);
                }

                school.addStudent(newStudent);
                dialog.dispose();
                // Refresh view
                parentFrame.setContentPane(new AdminViewPage(parentFrame, school).mainPanel);
                parentFrame.revalidate();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter a valid age.");
            }
        });

        dialog.pack();
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }

    private void openEditStudentDialog(JFrame parentFrame, String studentId) {
        // Find student
        Student student = null;
        for (Student s : school.getStudents()) {
            if (s.getId().equals(studentId)) {
                student = s;
                break;
            }
        }

        if (student == null) {
            JOptionPane.showMessageDialog(parentFrame, "Student not found.");
            return;
        }

        final Student finalStudent = student;

        JDialog dialog = new JDialog(parentFrame, "Edit Student", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components
        JTextField nameField = new JTextField(student.getName(), 20);
        JTextField ageField = new JTextField(String.valueOf(student.getAge()), 20);

        // Layout components
        gbc.gridx = 0; gbc.gridy = 0;
        dialog.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        dialog.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        dialog.add(new JLabel("Age:"), gbc);
        gbc.gridx = 1;
        dialog.add(ageField, gbc);

        JButton saveButton = new JButton("Save");
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        dialog.add(saveButton, gbc);

        saveButton.addActionListener(e -> {
            try {
                String newName = nameField.getText().trim();
                int newAge = Integer.parseInt(ageField.getText());

                // Validate inputs
                if (newName.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Name cannot be empty.");
                    return;
                }

                // Update the student's details
                finalStudent.setName(newName);
                finalStudent.setAge(newAge);

                dialog.dispose();
                // Refresh view
                parentFrame.setContentPane(new AdminViewPage(parentFrame, school).mainPanel);
                parentFrame.revalidate();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter a valid age.");
            }
        });

        dialog.pack();
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }
}