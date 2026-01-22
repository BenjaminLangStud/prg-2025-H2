package university.ui;

import university.Course;
import university.db.DatabaseConnector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class CoursesPanel extends UniDataPanel {
    private JTable table;
    private final List<Course> courses;
    private final DatabaseConnector dbc; // may be null

    public CoursesPanel(List<Course> courses, DatabaseConnector dbc) {
        super("Courses");
        this.courses = courses;
        this.dbc = dbc;
        initComponents();
        populateTable();

        refreshButton.addActionListener(e -> {
            if (this.dbc != null) {
                try {
                    java.util.ArrayList<Course> reloaded = this.dbc.getCourses();
                    this.courses.clear();
                    this.courses.addAll(reloaded);
                    populateTable();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(CoursesPanel.this,
                            "Could not reload courses from DB: " + ex.getMessage(),
                            "DB Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                populateTable();
            }
        });
    }

    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Course c : courses) {
            model.addRow(new Object[]{c.getId(), c.getName(), c.getForDegree()});
        }
        showDetails("Select a course to view details.");
    }

    @Override
    protected void initComponents() {
        table = new JTable(new DefaultTableModel(new Object[]{"ID", "Name", "ForDegree"}, 0));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(ev -> {
            int row = table.getSelectedRow();
            if (row >= 0 && row < courses.size()) {
                Course c = courses.get(row);
                StringBuilder sb = new StringBuilder();
                sb.append("ID: ").append(c.getId()).append('\n');
                sb.append("Name: ").append(c.getName()).append('\n');
                sb.append("Description: ").append(c.getDescription() == null ? "" : c.getDescription()).append('\n');
                sb.append("For Degree: ").append(c.getForDegree()).append('\n');
                showDetails(sb.toString());
            }
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row >= 0 && row < courses.size()) {
                        Course c = courses.get(row);
                        JOptionPane.showMessageDialog(CoursesPanel.this,
                                "Course details:\n" + c.getName() + "\n" + (c.getDescription() == null ? "" : c.getDescription()),
                                "Course",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        JSplitPane split = createMainWithDetails(table);
        add(split, BorderLayout.CENTER);
    }
}
