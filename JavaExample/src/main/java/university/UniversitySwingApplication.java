package university;

import university.utils.CSVReader;
import university.db.DatabaseConnector;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UniversitySwingApplication extends JFrame {

    public UniversitySwingApplication() {
        super("University Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        // try to load data; if it fails, use empty lists
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Professor> professors = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();

        DatabaseConnector dbc;

        // try database for students first (fallback to CSV)
        try {
            dbc = new DatabaseConnector("jdbc:postgresql://localhost:5432/prg", "postgres", "admin");
            try {
                students = dbc.getStudents();
            } catch (SQLException se) {
                System.err.println("Could not read students from DB, falling back to CSV: " + se.getMessage());
                try {
                    students = CSVReader.readStudents(UniversityApplication.DATA_PATH + "students.csv");
                } catch (IOException ioe) {
                    System.err.println("Could not read students from CSV: " + ioe.getMessage());
                }
            }

            // professors and courses still from CSV (keep original behavior)
            try {
                professors = CSVReader.readProfessors(UniversityApplication.DATA_PATH + "professors.csv");
                courses = CSVReader.readCourses(UniversityApplication.DATA_PATH + "courses.csv");
            } catch (IOException e) {
                System.err.println("Could not read CSV data for professors/courses: " + e.getMessage());
            }

        } catch (Exception e) {
            // catch-all: if DatabaseConnector construction or other unexpected exceptions occur, fallback to CSV for all
            System.err.println("Could not initialize DB connector, falling back to CSV for all data: " + e.getMessage());
            dbc = null;
            try {
                students = CSVReader.readStudents(UniversityApplication.DATA_PATH + "students.csv");
                professors = CSVReader.readProfessors(UniversityApplication.DATA_PATH + "professors.csv");
                courses = CSVReader.readCourses(UniversityApplication.DATA_PATH + "courses.csv");
            } catch (IOException ioe) {
                System.err.println("Could not read CSV data: " + ioe.getMessage());
            }
        }

        tabs.addTab("Students", new university.ui.StudentsPanel(students, dbc));
        tabs.addTab("Professors", new university.ui.ProfessorsPanel(professors, dbc));
        tabs.addTab("Courses", new university.ui.CoursesPanel(courses, dbc));

        add(tabs, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UniversitySwingApplication frame = new UniversitySwingApplication();
            frame.setVisible(true);
        });
    }

}
