package university.ui;

import university.Student;
import university.db.DatabaseConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class StudentsPanel extends UniDataPanel {
    private JList<String> list;
    private final List<Student> students;
    private final DatabaseConnector dbc; // may be null

    public StudentsPanel(List<Student> students, DatabaseConnector dbc) {
        super("Students");
        this.students = students;
        this.dbc = dbc;
        initComponents();
        populateList();

        // refresh action: try reload from DB if connector provided, otherwise just repopulate
        refreshButton.addActionListener(e -> {
            if (this.dbc != null) {
                try {
                    java.util.ArrayList<Student> reloaded = this.dbc.getStudents();
                    // replace contents of the list
                    this.students.clear();
                    this.students.addAll(reloaded);
                    populateList();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(StudentsPanel.this,
                            "Could not reload students from DB: " + ex.getMessage(),
                            "DB Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                populateList();
            }
        });
    }

    private void populateList() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Student s : students) {
            model.addElement(s.getId() + ": " + s.getFirstName() + " " + s.getLastName());
        }
        list.setModel(model);
        showDetails("Select a student to view details.");
    }

    @Override
    protected void initComponents() {
        list = new JList<>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(ev -> {
            int idx = list.getSelectedIndex();
            if (idx >= 0 && idx < students.size()) {
                Student s = students.get(idx);
                StringBuilder sb = new StringBuilder();
                sb.append("ID: ").append(s.getId()).append('\n');
                sb.append("Name: ").append(s.getFirstName()).append(' ').append(s.getLastName()).append('\n');
                sb.append("Age: ").append(s.getAge()).append('\n');
                if (s.getCourses() != null && !s.getCourses().isEmpty()) {
                    sb.append("Courses:\n");
                    for (int i = 0; i < s.getCourses().size(); i++) {
                        sb.append(" - ").append(s.getCourses().get(i).getName()).append('\n');
                    }
                }
                showDetails(sb.toString());
            }
        });

        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int idx = list.locationToIndex(e.getPoint());
                    if (idx >= 0 && idx < students.size()) {
                        Student s = students.get(idx);
                        JOptionPane.showMessageDialog(StudentsPanel.this,
                                "Student details:\n" + s.getFirstName() + " " + s.getLastName() + " (" + s.getAge() + ")",
                                "Student",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        JSplitPane split = createMainWithDetails(list);
        add(split, BorderLayout.CENTER);
    }
}
