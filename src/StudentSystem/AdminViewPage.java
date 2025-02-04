package StudentSystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class AdminViewPage {
    protected JPanel mainPanel;
    private School school;

    public AdminViewPage(JFrame frame, School school) {
        this.school = school;
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Header
        JLabel headerLabel = new JLabel("Student Management", SwingConstants.CENTER);
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
                    .reduce("", (a, b) -> a + (a.isEmpty() ? "" : ", ") + b);

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

        // Course selection
        DefaultListModel<Course> courseListModel = new DefaultListModel<>();
        for (Course course : school.getCourses()) {
            courseListModel.addElement(course);
        }
        JList<Course> availableCoursesList = new JList<>(courseListModel);
        availableCoursesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        availableCoursesList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);
                if (value instanceof Course) {
                    Course course = (Course) value;
                    label.setText(course.getCourseCode() + " - " + course.getCourseName() +
                            " (Instructor: " + course.getInstructor() + ")");
                }
                return label;
            }
        });
        JScrollPane courseScrollPane = new JScrollPane(availableCoursesList);
        courseScrollPane.setPreferredSize(new Dimension(300, 150));

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

        gbc.gridx = 0; gbc.gridy = 4;
        dialog.add(new JLabel("Courses:"), gbc);
        gbc.gridx = 1;
        dialog.add(courseScrollPane, gbc);

        JButton saveButton = new JButton("Save");
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        dialog.add(saveButton, gbc);

        saveButton.addActionListener(e -> {
            try {
                // Validate inputs
                String name = nameField.getText().trim();
                String id = idField.getText().trim();
                int age = Integer.parseInt(ageField.getText());
                String type = (String) typeCombo.getSelectedItem();

                // Validate name and ID
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Name cannot be empty.");
                    return;
                }
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "ID cannot be empty.");
                    return;
                }

                // Create student
                Student newStudent;
                if ("National".equals(type)) {
                    newStudent = new NationalStudent(name, id, age);
                } else {
                    newStudent = new IgStudent(name, id, age);
                }

                // Add selected courses
                List<Course> selectedCourses = availableCoursesList.getSelectedValuesList();
                for (Course course : selectedCourses) {
                    newStudent.addCourse(course);
                }

                // Add student to school
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

        // Course selection with more details
        DefaultListModel<Course> courseListModel = new DefaultListModel<>();
        // Add available courses
        for (Course course : school.getCourses()) {
            courseListModel.addElement(course);
        }
        JList<Course> availableCoursesList = new JList<>(courseListModel);
        availableCoursesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Pre-select student's current courses
        List<Course> studentCourses = student.getCourses();
        int[] selectedIndices = new int[studentCourses.size()];
        for (int i = 0; i < studentCourses.size(); i++) {
            Course studentCourse = studentCourses.get(i);
            for (int j = 0; j < courseListModel.size(); j++) {
                if (courseListModel.get(j).getCourseCode().equals(studentCourse.getCourseCode())) {
                    selectedIndices[i] = j;
                    break;
                }
            }
        }
        availableCoursesList.setSelectedIndices(selectedIndices);

        // Custom cell renderer
        availableCoursesList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);
                if (value instanceof Course) {
                    Course course = (Course) value;
                    label.setText(course.getCourseCode() + " - " + course.getCourseName() +
                            " (Instructor: " + course.getInstructor() + ")");
                }
                return label;
            }
        });

        JScrollPane courseScrollPane = new JScrollPane(availableCoursesList);
        courseScrollPane.setPreferredSize(new Dimension(300, 150));

        // Layout components
        gbc.gridx = 0; gbc.gridy = 0;
        dialog.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        dialog.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        dialog.add(new JLabel("Age:"), gbc);
        gbc.gridx = 1;
        dialog.add(ageField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        dialog.add(new JLabel("Courses:"), gbc);
        gbc.gridx = 1;
        dialog.add(courseScrollPane, gbc);

        JButton saveButton = new JButton("Save");
        gbc.gridx = 0; gbc.gridy = 3;
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

                // Update the student's basic details
                finalStudent.setName(newName);
                finalStudent.setAge(newAge);

                // Clear existing courses
                finalStudent.getCourses().clear();

                // Add selected courses
                List<Course> selectedCourses = availableCoursesList.getSelectedValuesList();
                for (Course course : selectedCourses) {
                    finalStudent.addCourse(course);
                }

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