package university.utils;
import org.apache.commons.io.FileUtils;
import university.Course;
import university.Professor;
import university.Student;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class CSVReader {

    public static List<String> readCSV(String filePath) throws IOException {
        return FileUtils.readLines(new File(filePath), "UTF-8");
    }


    public static ArrayList<Student> readStudents(String filePath) throws IOException {
        List<String> lines = readCSV(filePath);
        ArrayList<Student> students = new ArrayList<>();
        // Further processing can be done here to convert lines into Student objects
        int count = 0;
        for (String line : lines) {
            // skip the header line
            if (count > 0) {
                String[] values = line.split(";");
                Student student = new Student(Integer.parseInt(values[0]), values[1], values[2], Integer.parseInt(values[3]));
                students.add(student);
            }
            count++;
        }
        return students;
    }

    public static ArrayList<Course> readCourses(String filePath) throws IOException {
        List<String> lines = readCSV(filePath);
        ArrayList<Course> courses = new ArrayList<Course>();
        // Further processing can be done here to convert lines into Student objects
        int count = 0;
        for (String line : lines) {
            // skip the header line
            if (count > 0) {
                String[] values = line.split(";");
                Course course = new Course(Integer.parseInt(values[0]), values[1], values[2], values[3]);
                courses.add(course);
            }
            count++;
        }
        return courses;
    }

    public static ArrayList<Professor> readProfessors(String filePath) throws IOException {
        List<String> lines = readCSV(filePath);
        ArrayList<Professor> professors = new ArrayList<Professor>();
        // Further processing can be done here to convert lines into Student objects
        int count = 0;
        for (String line : lines) {
            // skip the header line
            if (count > 0) {
                String[] values = line.split(";");
                Professor professor = new Professor(Integer.parseInt(values[0]), values[1], values[2], values[3]);
                professors.add(professor);
            }
            count++;
        }
        return professors;
    }

    public static void assignCoursesToStudents(ArrayList<Student> students, ArrayList<Course> courses, String filePath) throws IOException {
        // Example logic to assign courses to students
        List<String> lines = CSVReader.readCSV(filePath);
        int count =0;
        for (String line : lines) {
            if (count > 0) {
                String[] values = line.split(";");
                int studentId = Integer.parseInt(values[0]);
                int courseId = Integer.parseInt(values[1]);
                Student student = ListUtils.findStudentById(studentId, students);
                Course course = ListUtils.findCourseById(courseId, courses);
                if (student != null && course != null) {
                    student.enrollCourse(course);
                }
            }
            count++;
        }
    }


    public static void assignCoursesToProfessors(ArrayList<Professor> professors, ArrayList<Course> courses, String filePath) throws IOException {
        // Example logic to assign courses to students
        List<String> lines = CSVReader.readCSV(filePath);
        int count =0;
        for (String line : lines) {
            if (count > 0) {
                String[] values = line.split(";");
                int profId = Integer.parseInt(values[0]);
                int courseId = Integer.parseInt(values[1]);
                Professor professor = ListUtils.findProfessorById(profId, professors);
                Course course = ListUtils.findCourseById(courseId, courses);
                if (professor != null && course != null) {
                    System.out.println("Assigning course " + course.getName() + " to professor " + professor.firstName);
                    professor.addCourseTeaching(course);
                }
            }
            count++;
        }
    }
}
