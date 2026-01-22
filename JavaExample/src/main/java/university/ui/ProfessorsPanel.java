package university.ui;

import university.Professor;
import university.db.DatabaseConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class ProfessorsPanel extends UniDataPanel {
    private JList<String> list;
    private final List<Professor> professors;
    private final DatabaseConnector dbc; // may be null

    public ProfessorsPanel(List<Professor> professors, DatabaseConnector dbc) {
        super("Professors");
        this.professors = professors;
        this.dbc = dbc;
        initComponents();
        populateList();

        refreshButton.addActionListener(e -> {
            if (this.dbc != null) {
                try {
                    java.util.ArrayList<Professor> reloaded = this.dbc.getProfessors();
                    this.professors.clear();
                    this.professors.addAll(reloaded);
                    populateList();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(ProfessorsPanel.this,
                            "Could not reload professors from DB: " + ex.getMessage(),
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
        for (Professor p : professors) {
            model.addElement(p.getId() + ": " + p.getFirstName() + " " + p.getLastName());
        }
        list.setModel(model);
        showDetails("Select a professor to view details.");
    }

    @Override
    protected void initComponents() {
        list = new JList<>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(ev -> {
            int idx = list.getSelectedIndex();
            if (idx >= 0 && idx < professors.size()) {
                Professor p = professors.get(idx);
                StringBuilder sb = new StringBuilder();
                sb.append("ID: ").append(p.getId()).append('\n');
                sb.append("Name: ").append(p.getFirstName()).append(' ').append(p.getLastName()).append('\n');
                sb.append("Office: ").append(p.getOfficeNumber()).append('\n');
                if (p.getCoursesTeaching() != null && !p.getCoursesTeaching().isEmpty()) {
                    sb.append("Courses Teaching:\n");
                    for (int i = 0; i < p.getCoursesTeaching().size(); i++) {
                        sb.append(" - ").append(p.getCoursesTeaching().get(i).getName()).append('\n');
                    }
                }
                showDetails(sb.toString());
            }
        });

        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int idx = list.locationToIndex(e.getPoint());
                    if (idx >= 0 && idx < professors.size()) {
                        Professor p = professors.get(idx);
                        JOptionPane.showMessageDialog(ProfessorsPanel.this,
                                "Professor details:\n" + p.getFirstName() + " " + p.getLastName() + " (Office: " + p.getOfficeNumber() + ")",
                                "Professor",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        JSplitPane split = createMainWithDetails(list);
        add(split, BorderLayout.CENTER);
    }
}
