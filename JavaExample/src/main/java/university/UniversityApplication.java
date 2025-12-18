package university;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import university.utils.CSVReader;

public class UniversityApplication {
    private ArrayList<Student> students;
    private ArrayList<Professor> professors;
    private ArrayList<Course> courses;
    public UniversityApplication() {
        this.loadData();
    }

    private void loadData() {
        try {
            this.students = CSVReader.readStudents("C:\\srh\\github\\prg-2025-H2\\JavaExample\\src\\main\\java\\university\\data\\students.csv");
            this.courses = CSVReader.readCourses("C:\\srh\\github\\prg-2025-H2\\JavaExample\\src\\main\\java\\university\\data\\courses.csv");
            this.professors = CSVReader.readProfessors("C:\\srh\\github\\prg-2025-H2\\JavaExample\\src\\main\\java\\university\\data\\professors.csv");
            CSVReader.assignCoursesToStudents(students, courses, "C:\\srh\\github\\prg-2025-H2\\JavaExample\\src\\main\\java\\university\\data\\student-course-assignment.csv");
            CSVReader.assignCoursesToProfessors(professors, courses, "C:\\srh\\github\\prg-2025-H2\\JavaExample\\src\\main\\java\\university\\data\\professor-course-assignment.csv");
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

    public static void main(String[] args) {
        UniversityApplication app = new UniversityApplication();

        /*
        Student student = new Student(1,"John", "Doe", 20);
        student.enrollCourse(new Course(0,"Mathematics", "Basic Math Course", "Master"));
        student.enrollCourse(new Course(1, "Computer Science", "Advance Computer Science", "Master"));
        student.printInformation();
        */

    }
    /*
    public static void createPersons() {
        Person john = new Person();
        john.setFirstName("John");
        john.setLastName("Doe");
        Person jane = new Person("Jane", "Smith");
        System.out.println(john.getLastName());
        System.out.println(jane.getLastName());
    }

    public static ArrayList<Degree> getDegrees() {
        ArrayList<Degree> degrees = new ArrayList<>();
        degrees.add(new Degree("Bachelor"));
        degrees.add(new Degree("Master"));
        degrees.add(new Degree("PhD"));
        return degrees;
    }

    public static ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course(0, "Mathematics", "Basic Math Course", "Bachelor"));
        courses.add(new Course(1,"Computer Science", "Intro to Computer Science", "Bachelor"));
        courses.add(new Course(2,"Physics", "Fundamentals of Physics", "Bachelor"));
        courses.add(new Course(3,"Chemistry", "General Chemistry", "Bachelor"));
        courses.add(new Course(4,"Data Structures", "In-depth study of data structures", "Master"));
        courses.add(new Course(5,"Algorithms", "Algorithm design and analysis", "Master"));
        courses.add(new Course(6, "Philosophy", "Introduction to Philosophy", "Bachelor"));
        return courses;
    }

    public static ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(2,"Alice", "Smith", 22));

        students.add(new Student(3,"Bob", "Johnson", 24));
        return students;
    }

     */
}
