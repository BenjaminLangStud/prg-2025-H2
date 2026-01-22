package university;

import java.io.IOException;
import java.util.ArrayList;

import university.db.DatabaseConnector;
import university.utils.CSVReader;

public class UniversityApplication {
    // TODO: set here the folder to your local data
    public static final String DATA_PATH = "C:\\srh\\github\\prg-2025-H2\\JavaExample\\src\\main\\java\\university\\data\\";
    private ArrayList<Student> students;
    private ArrayList<Professor> professors;
    private ArrayList<Course> courses;

    public UniversityApplication() {
    }

    private void loadData() {
        try {
            this.students = CSVReader.readStudents(DATA_PATH + "students.csv");
            this.courses = CSVReader.readCourses(DATA_PATH +"courses.csv");
            this.professors = CSVReader.readProfessors(DATA_PATH +"professors.csv");
            CSVReader.assignCoursesToStudents(students, courses, DATA_PATH +"student-course-assignment.csv");
            CSVReader.assignCoursesToProfessors(professors, courses, DATA_PATH +"professor-course-assignment.csv");
            System.out.println("Students loaded: " + this.students.size());
            System.out.println("Courses loaded: " + this.courses.size());
            System.out.println("Professors loaded: " + this.professors.size());
            System.out.println();
            this.students.get(0).printInformation();
            System.out.println();
            this.professors.get(0).printInformation();
        } catch (IOException e) {
            System.out.println(("Error reading  CSV: " + e.getMessage()));
        }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }


    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(ArrayList<Professor> professors) {
        this.professors = professors;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public void saveDataToDB() throws Exception {
        DatabaseConnector dbc = new DatabaseConnector("jdbc:postgresql://localhost:5432/prg", "postgres", "admin");
        for (Student student: this.students) {
            dbc.insertStudent(student);
        }
        for (Professor professor: this.professors) {
            dbc.insertProfessor(professor);
        }
        for (Course course: this.courses) {
            dbc.insertCourse(course);
        }
        for (Student student: this.students) {
            for (Course course: student.getCourses()) {
                dbc.assignCourseToStudent(student.getId(), course.getId());
            }
        }
        for (Professor professor: this.professors) {
            for (Course course: professor.getCoursesTeaching()) {
                dbc.assignCourseToProfessor(professor.getId(), course.getId());
            }
        }
    }

    public static void main(String[] args) {
        DatabaseConnector dbc = new DatabaseConnector("jdbc:postgresql://localhost:5432/prg", "postgres", "admin");
        dbc.cleanDB();

        UniversityApplication app = new UniversityApplication();
        // now data is loaded
        app.loadData();
        try {
            app.saveDataToDB();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
